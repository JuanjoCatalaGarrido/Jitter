package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.PostDetailsDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.core.mapper.PostMapper;
import by.juanjo.jitter.rest.controller.PostController;
import by.juanjo.jitter.rest.exception.PostNotFoundException;
import by.juanjo.jitter.rest.service.PostService;
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
  private final PostMapper postMapper;

  @Autowired
  public PostControllerImpl(PostService postService, PostMapper postMapper) {
    this.postService = postService;
    this.postMapper = postMapper;
  }

  @ApiResponse(responseCode = "201", description = "Post created successfully", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided post is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<PostSummaryDTO> create(@RequestBody @NotNull PostSummaryDTO postDTO) {
    Post newPost = postMapper.toEntity(postDTO);
    Post savedPost = this.postService.save(newPost);

    PostSummaryDTO savedPostDTO = this.postMapper.toPostSummaryDTO(savedPost);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPostDTO);
  }


  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Post found", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<PostSummaryDTO> read(@PathVariable Long id) throws PostNotFoundException {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);

    Post post = possiblyFoundPost.orElseThrow(
        () -> new PostNotFoundException(String.format("Couldn't find post with id: %d", id)));

    PostSummaryDTO postDTO = this.postMapper.toPostSummaryDTO(post);
    return ResponseEntity.ok(postDTO);
  }

  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Post updated successfully", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<PostSummaryDTO> update(@RequestBody @NotNull PostSummaryDTO newPostDTO,
      @PathVariable Long id) {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);
    if (!possiblyFoundPost.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Post postToBeUpdated = possiblyFoundPost.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newPostDTO, postToBeUpdated, IGNORE_ATTRIB);

    Post updatedPost = this.postService.save(postToBeUpdated);

    PostSummaryDTO postDTO = this.postMapper.toPostSummaryDTO(updatedPost);
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
    if (!this.postService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    this.postService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Posts retrieved successfully", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<PostSummaryDTO>> getAll() {
    Iterable<Post> posts = this.postService.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<PostSummaryDTO> allPostsDTO = StreamSupport.stream(posts.spliterator(), CONCURRENT_FLAG)
        .map(this.postMapper::toPostSummaryDTO).toList();

    return ResponseEntity.ok(allPostsDTO);
  }

  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<PostSummaryDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable postsSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by("postname").descending());

    Page<PostSummaryDTO> page = this.postService.findAll(postsSortedByNameDesc)
        .map(this.postMapper::toPostSummaryDTO);

    return ResponseEntity.ok(page);
  }

  // ----------------------------------------------------------

  @ApiResponse(responseCode = "404", description = "Post not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Post found", content = {
      @Content(schema = @Schema(implementation = PostDetailsDTO.class))})
  @Parameter(name = "id", description = "The post's id", example = "1", required = true)
  @GetMapping("/{id}/details")
  @Override
  public ResponseEntity<PostDetailsDTO> servePostDetails(@PathVariable Long id)
      throws PostNotFoundException {
    Optional<Post> possiblyFoundPost = this.postService.findById(id);

    Post post = possiblyFoundPost.orElseThrow(
        () -> new PostNotFoundException(String.format("Couldn't find post with id: %d", id)));

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
  public ResponseEntity<List<PostSummaryDTO>> servePostsByUserId(
      @PathVariable(value = "id") @NotNull Long userID) {

    List<PostSummaryDTO> posts = this.postService.findByOwnerId(userID).stream()
        .map(this.postMapper::toPostSummaryDTO).toList();

    if (posts.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(posts);
  }

}
