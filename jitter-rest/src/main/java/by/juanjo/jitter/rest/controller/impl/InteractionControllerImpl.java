package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.InteractionDTO;
import by.juanjo.jitter.core.entity.Interaction;
import by.juanjo.jitter.core.mapper.InteractionMapper;
import by.juanjo.jitter.rest.controller.InteractionController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.InteractionService;
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
@RequestMapping("/api/interactions")
public class InteractionControllerImpl implements InteractionController {

  private InteractionService interactionService;
  private InteractionMapper interactionMapper;

  @Autowired
  public InteractionControllerImpl(InteractionService interactionService,
      InteractionMapper interactionMapper) {
    this.interactionService = interactionService;
    this.interactionMapper = interactionMapper;
  }

  @ApiResponse(responseCode = "201", description = "Interaction created successfully", content = {
      @Content(schema = @Schema(implementation = InteractionDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided interaction is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<InteractionDTO> create(
      @RequestBody @NotNull InteractionDTO interactionDTO) {
    Interaction newInteraction = interactionMapper.toEntity(interactionDTO);
    Interaction savedInteraction = this.interactionService.save(newInteraction);

    InteractionDTO savedInteractionDTO = this.interactionMapper.toDTO(savedInteraction);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedInteractionDTO);
  }


  @ApiResponse(responseCode = "404", description = "Interaction not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Interaction found", content = {
      @Content(schema = @Schema(implementation = InteractionDTO.class))})
  @Parameter(name = "id", description = "The interaction's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<InteractionDTO> read(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Interaction> possiblyFoundInteraction = this.interactionService.findById(id);

    Interaction interaction = possiblyFoundInteraction.orElseThrow(
        () -> new ElementNotFoundException(
            String.format("Couldn't find interaction with id: %d", id)));

    InteractionDTO interactionDTO = this.interactionMapper.toDTO(interaction);
    return ResponseEntity.ok(interactionDTO);
  }

  @ApiResponse(responseCode = "404", description = "Interaction not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Interaction updated successfully", content = {
      @Content(schema = @Schema(implementation = InteractionDTO.class))})
  @Parameter(name = "id", description = "The interaction's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<InteractionDTO> update(
      @RequestBody @NotNull InteractionDTO newInteractionDTO,
      @PathVariable Long id) {
    Optional<Interaction> possiblyFoundInteraction = this.interactionService.findById(id);
    if (!possiblyFoundInteraction.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Interaction interactionToBeUpdated = possiblyFoundInteraction.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newInteractionDTO, interactionToBeUpdated, IGNORE_ATTRIB);

    Interaction updatedInteraction = this.interactionService.save(interactionToBeUpdated);

    InteractionDTO interactionDTO = this.interactionMapper.toDTO(updatedInteraction);
    return ResponseEntity.status(HttpStatus.CREATED).body(interactionDTO);
  }

  @ApiResponse(responseCode = "404", description = "Interaction not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Interaction deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The interaction's id", example = "1", required = true)
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (!this.interactionService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    this.interactionService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Interactions retrieved successfully", content = {
      @Content(schema = @Schema(implementation = InteractionDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<InteractionDTO>> getAll() {
    Iterable<Interaction> interactions = this.interactionService.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<InteractionDTO> allInteractionsDTO = StreamSupport.stream(interactions.spliterator(),
            CONCURRENT_FLAG)
        .map(this.interactionMapper::toDTO).toList();

    return ResponseEntity.ok(allInteractionsDTO);
  }

  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<InteractionDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable interactionsSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by("interactionname").descending());

    Page<InteractionDTO> page = this.interactionService.findAll(interactionsSortedByNameDesc)
        .map(this.interactionMapper::toDTO);

    return ResponseEntity.ok(page);
  }


}
