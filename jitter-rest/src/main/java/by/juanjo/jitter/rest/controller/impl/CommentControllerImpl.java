package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.CommentDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.core.entity.Comment;
import by.juanjo.jitter.core.entity.Comment_;
import by.juanjo.jitter.core.mapper.CommentMapper;
import by.juanjo.jitter.rest.controller.CommentController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.CommentService;
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
@RequestMapping("/api/comments")
public class CommentControllerImpl implements CommentController {

  private final CommentService commentService;
  private final CommentMapper commentMapper;

  @Autowired
  public CommentControllerImpl(CommentService commentService, CommentMapper commentMapper) {
    this.commentService = commentService;
    this.commentMapper = commentMapper;
  }

  @ApiResponse(responseCode = "201", description = "Comment created successfully", content = {
      @Content(schema = @Schema(implementation = CommentDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided comment is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<CommentDTO> create(@RequestBody @NotNull CommentDTO dto) {
    Comment newComment = commentMapper.toEntity(dto);
    Comment savedComment = this.commentService.save(newComment);

    CommentDTO savedCommentDTO = this.commentMapper.toDTO(savedComment);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentDTO);
  }

  @ApiResponse(responseCode = "404", description = "Comment not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Comment found", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @Parameter(name = "id", description = "The comment's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<CommentDTO> read(@PathVariable Long id) throws ElementNotFoundException {
    Optional<Comment> possiblyFoundPost = this.commentService.findById(id);

    Comment comment = possiblyFoundPost.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find comment with id: %d", id)));

    CommentDTO commentDTO = this.commentMapper.toDTO(comment);
    return ResponseEntity.ok(commentDTO);
  }


  @ApiResponse(responseCode = "404", description = "Comment found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Comment updated successfully", content = {
      @Content(schema = @Schema(implementation = CommentDTO.class))})
  @Parameter(name = "id", description = "The comment's id", example = "1", required = true)
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<CommentDTO> update(@RequestBody @NotNull CommentDTO dto,
      @PathVariable Long id) {
    Optional<Comment> possiblyFoundComment = this.commentService.findById(id);
    if (possiblyFoundComment.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Comment commentToBeUpdated = possiblyFoundComment.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(dto, commentToBeUpdated, IGNORE_ATTRIB);

    Comment updatedComment = this.commentService.save(commentToBeUpdated);

    CommentDTO commentDTO = this.commentMapper.toDTO(updatedComment);
    return ResponseEntity.status(HttpStatus.CREATED).body(commentDTO);
  }

  @ApiResponse(responseCode = "404", description = "Comment not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Comment deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The comment's id", example = "1", required = true)
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (this.commentService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    this.commentService.deleteById(id);
    return ResponseEntity.ok().build();
  }


  @ApiResponse(responseCode = "200", description = "Comments retrieved successfully", content = {
      @Content(schema = @Schema(implementation = CommentDTO.class))})
  @GetMapping
  @Override
  public ResponseEntity<List<CommentDTO>> getAll() {
    Iterable<Comment> users = this.commentService.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<CommentDTO> allCommentsDTO = StreamSupport.stream(users.spliterator(), CONCURRENT_FLAG)
        .map(this.commentMapper::toDTO).toList();

    return ResponseEntity.ok(allCommentsDTO);
  }

  @ApiResponse(responseCode = "200", description = "Comment retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @GetMapping("/paginate/{page}")
  @Override
  public ResponseEntity<Page<CommentDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable usersSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by(Comment_.CREATED_AT).descending());

    Page<CommentDTO> page = this.commentService.findAll(usersSortedByNameDesc)
        .map(this.commentMapper::toDTO);

    return ResponseEntity.ok(page);
  }
}
