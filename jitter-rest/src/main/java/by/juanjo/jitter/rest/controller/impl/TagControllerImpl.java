package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.TagDTO;
import by.juanjo.jitter.core.entity.Tag;
import by.juanjo.jitter.core.entity.Tag_;
import by.juanjo.jitter.core.mapper.TagMapper;
import by.juanjo.jitter.rest.controller.TagController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.TagService;
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
@RequestMapping("/api/tags")
public class TagControllerImpl implements TagController {

  private final TagService service;
  private final TagMapper mapper;

  @Autowired
  public TagControllerImpl(TagService service, TagMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @ApiResponse(responseCode = "201", description = "Tag created successfully", content = {
      @Content(schema = @Schema(implementation = TagDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided tag is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<TagDTO> create(@RequestBody @NotNull TagDTO tagDTO) {
    Tag newTag = mapper.toEntity(tagDTO);
    Tag savedTag = this.service.save(newTag);

    TagDTO savedTagDTO = this.mapper.toDTO(savedTag);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTagDTO);
  }


  @ApiResponse(responseCode = "404", description = "Tag not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Tag found", content = {
      @Content(schema = @Schema(implementation = TagDTO.class))})
  @Parameter(name = "id", description = "The tag's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<TagDTO> read(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Tag> possiblyFoundTag = this.service.findById(id);

    Tag tag = possiblyFoundTag.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find tag with id: %d", id)));

    TagDTO tagDTO = this.mapper.toDTO(tag);
    return ResponseEntity.ok(tagDTO);
  }

  @ApiResponse(responseCode = "404", description = "Tag not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Tag updated successfully", content = {
      @Content(schema = @Schema(implementation = TagDTO.class))})
  @Parameter(name = "id", description = "The tag's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<TagDTO> update(@RequestBody @NotNull TagDTO newTagDTO,
      @PathVariable Long id) {
    Optional<Tag> possiblyFoundTag = this.service.findById(id);
    if (possiblyFoundTag.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Tag tagToBeUpdated = possiblyFoundTag.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newTagDTO, tagToBeUpdated, IGNORE_ATTRIB);

    Tag updatedTag = this.service.save(tagToBeUpdated);

    TagDTO tagDTO = this.mapper.toDTO(updatedTag);
    return ResponseEntity.status(HttpStatus.CREATED).body(tagDTO);
  }

  @ApiResponse(responseCode = "404", description = "Tag not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Tag deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The tag's id", example = "1", required = true)
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (this.service.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    this.service.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Tags retrieved successfully", content = {
      @Content(schema = @Schema(implementation = TagDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<TagDTO>> getAll() {
    Iterable<Tag> tags = this.service.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<TagDTO> allTagsDTO = StreamSupport.stream(tags.spliterator(), CONCURRENT_FLAG)
        .map(this.mapper::toDTO).toList();

    return ResponseEntity.ok(allTagsDTO);
  }


  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<TagDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable tagsSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by(Tag_.NAME).descending());

    Page<TagDTO> page = this.service.findAll(tagsSortedByNameDesc)
        .map(this.mapper::toDTO);

    return ResponseEntity.ok(page);
  }

  @ApiResponse(responseCode = "404", description = "Tag not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Tags found", content = {
      @Content(schema = @Schema(implementation = TagDTO.class))})
  @GetMapping("/trending")
  @Override
  public ResponseEntity<List<TagDTO>> serveTagsOrderedByPostsCount() {
    List<TagDTO> tags = this.service.serveTagsOrderByPostNum().stream().map(this.mapper::toDTO)
        .toList();
    return ResponseEntity.ok(tags);

  }


}
