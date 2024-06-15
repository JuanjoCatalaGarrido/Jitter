package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.PostDTO;
import by.juanjo.jitter.core.dto.PostDetailsDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.core.entity.Post_;
import by.juanjo.jitter.core.mapper.PostMapper;
import by.juanjo.jitter.rest.controller.PostController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.PostService;
import by.juanjo.jitter.rest.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostControllerImpl implements PostController {

  private final PostService postService;
  private final UserService userService;
  private final PostMapper postMapper;

  @Autowired
  public PostControllerImpl(PostService postService, UserService userService,
      PostMapper postMapper) {
    this.postService = postService;
    this.userService = userService;
    this.postMapper = postMapper;
  }

  @ApiResponse(responseCode = "201", description = "Post created successfully", content = {
      @Content(schema = @Schema(implementation = PostDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided post is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<PostDTO> create(@RequestBody @NotNull PostDTO postDTO) {
    Post newPost = postMapper.toEntity(postDTO);
    Post savedPost = this.postService.save(newPost);

    PostDTO savedPostDTO = this.postMapper.toDTO(savedPost);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPostDTO);
  }


  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Post found", content = {
      @Content(schema = @Schema(implementation = PostDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<PostDTO> read(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);

    Post post = possiblyFoundPost.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find post with id: %d", id)));

    PostDTO postDTO = this.postMapper.toDTO(post);
    return ResponseEntity.ok(postDTO);
  }

  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Post updated successfully", content = {
      @Content(schema = @Schema(implementation = PostDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<PostDTO> update(@RequestBody @NotNull PostDTO newPostDTO,
      @PathVariable Long id) {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);
    if (possiblyFoundPost.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Post postToBeUpdated = possiblyFoundPost.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newPostDTO, postToBeUpdated, IGNORE_ATTRIB);

    Post updatedPost = this.postService.save(postToBeUpdated);

    PostDTO postDTO = this.postMapper.toDTO(updatedPost);
    return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
  }

  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Post deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (this.postService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    this.postService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Posts retrieved successfully", content = {
      @Content(schema = @Schema(implementation = PostDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<PostDTO>> getAll() {
    Iterable<Post> posts = this.postService.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<PostDTO> allPostsDTO = StreamSupport.stream(posts.spliterator(), CONCURRENT_FLAG)
        .map(this.postMapper::toDTO).toList();

    return ResponseEntity.ok(allPostsDTO);
  }

  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<PostDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable postsSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by(Post_.CREATED_AT).descending());

    Page<PostDTO> page = this.postService.findAll(postsSortedByNameDesc)
        .map(this.postMapper::toDTO);

    return ResponseEntity.ok(page);
  }

  // ----------------------------------------------------------


  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Post found", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @GetMapping("/{id}/summary")
  @Override
  public ResponseEntity<PostSummaryDTO> servePostSummary(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);

    Post post = possiblyFoundPost.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find post with id: %d", id)));

    PostSummaryDTO postDTO = this.postMapper.toPostSummaryDTO(post);
    return ResponseEntity.ok(postDTO);
  }


  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Post found", content = {
      @Content(schema = @Schema(implementation = PostDetailsDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @GetMapping("/{id}/details")
  @Override
  public ResponseEntity<PostDetailsDTO> servePostDetails(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);

    Post post = possiblyFoundPost.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find post with id: %d", id)));

    PostDetailsDTO postDTO = this.postMapper.toPostDetailsDTO(post);
    return ResponseEntity.ok(postDTO);
  }


  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @ApiResponse(responseCode = "204", description = "No posts created by user that id", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "id", description = "The user ID", example = "0", required = true)
  @Override
  @GetMapping("/fromUser/{id}")
  public ResponseEntity<List<PostDTO>> servePostsByUserId(
      @PathVariable(value = "id") @NotNull Long userID) {

    List<PostDTO> posts = this.postService.findByOwnerId(userID).stream()
        .map(this.postMapper::toDTO).toList();

    if (posts.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(posts);
  }

}
