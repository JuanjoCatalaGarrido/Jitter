package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.EmailVerificationCodeDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.core.entity.EmailVerificationCode;
import by.juanjo.jitter.core.mapper.EmailVerificationCodeMapper;
import by.juanjo.jitter.rest.controller.EmailVerificationCodeController;
import by.juanjo.jitter.rest.exception.PostNotFoundException;
import by.juanjo.jitter.rest.service.EmailVerificationCodeService;
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
@RequestMapping("/api/emailVerificationCodes")
public class EmailVerificationCodeControllerImpl implements EmailVerificationCodeController {

  private EmailVerificationCodeService emailVerificationCodeService;
  private EmailVerificationCodeMapper mapper;

  @Autowired
  public EmailVerificationCodeControllerImpl(
      EmailVerificationCodeService emailVerificationCodeService,
      EmailVerificationCodeMapper mapper) {
    this.emailVerificationCodeService = emailVerificationCodeService;
    this.mapper = mapper;
  }

  @ApiResponse(responseCode = "201", description = "EmailVerificationCode created successfully", content = {
      @Content(schema = @Schema(implementation = EmailVerificationCodeDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided emailVerificationCode is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<EmailVerificationCodeDTO> create(
      @RequestBody @NotNull EmailVerificationCodeDTO dto) {
    EmailVerificationCode newEmailVerificationCode = mapper.toEntity(dto);
    EmailVerificationCode savedEmailVerificationCode = this.emailVerificationCodeService.save(
        newEmailVerificationCode);

    EmailVerificationCodeDTO savedEmailVerificationCodeDTO = this.mapper.toDTO(
        savedEmailVerificationCode);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedEmailVerificationCodeDTO);
  }

  @ApiResponse(responseCode = "404", description = "EmailVerificationCode not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "EmailVerificationCode found", content = {
      @Content(schema = @Schema(implementation = PostSummaryDTO.class))})
  @Parameter(name = "id", description = "The emailVerificationCode's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<EmailVerificationCodeDTO> read(@PathVariable Long id) {
    Optional<EmailVerificationCode> possiblyFoundPost = this.emailVerificationCodeService.findById(
        id);

    EmailVerificationCode emailVerificationCode = possiblyFoundPost.orElseThrow(
        () -> new PostNotFoundException(
            String.format("Couldn't find emailVerificationCode with id: %d", id)));

    EmailVerificationCodeDTO emailVerificationCodeDTO = this.mapper.toDTO(emailVerificationCode);
    return ResponseEntity.ok(emailVerificationCodeDTO);
  }


  @ApiResponse(responseCode = "404", description = "EmailVerificationCode found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "EmailVerificationCode updated successfully", content = {
      @Content(schema = @Schema(implementation = EmailVerificationCodeDTO.class))})
  @Parameter(name = "id", description = "The emailVerificationCode's id", example = "1", required = true)
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<EmailVerificationCodeDTO> update(
      @RequestBody @NotNull EmailVerificationCodeDTO dto, @PathVariable Long id) {
    Optional<EmailVerificationCode> possiblyFoundEmailVerificationCode = this.emailVerificationCodeService.findById(
        id);
    if (!possiblyFoundEmailVerificationCode.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    EmailVerificationCode emailVerificationCodeToBeUpdated = possiblyFoundEmailVerificationCode.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(dto, emailVerificationCodeToBeUpdated, IGNORE_ATTRIB);

    EmailVerificationCode updatedEmailVerificationCode = this.emailVerificationCodeService.save(
        emailVerificationCodeToBeUpdated);

    EmailVerificationCodeDTO emailVerificationCodeDTO = this.mapper.toDTO(
        updatedEmailVerificationCode);
    return ResponseEntity.status(HttpStatus.CREATED).body(emailVerificationCodeDTO);
  }

  @ApiResponse(responseCode = "404", description = "EmailVerificationCode not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "EmailVerificationCode deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The emailVerificationCode's id", example = "1", required = true)
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (!this.emailVerificationCodeService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    this.emailVerificationCodeService.deleteById(id);
    return ResponseEntity.ok().build();
  }


  @ApiResponse(responseCode = "200", description = "EmailVerificationCodes retrieved successfully", content = {
      @Content(schema = @Schema(implementation = EmailVerificationCodeDTO.class))})
  @GetMapping
  @Override
  public ResponseEntity<List<EmailVerificationCodeDTO>> getAll() {
    Iterable<EmailVerificationCode> users = this.emailVerificationCodeService.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<EmailVerificationCodeDTO> allEmailVerificationCodesDTO = StreamSupport.stream(
            users.spliterator(), CONCURRENT_FLAG)
        .map(this.mapper::toDTO).toList();

    return ResponseEntity.ok(allEmailVerificationCodesDTO);
  }

  @ApiResponse(responseCode = "200", description = "EmailVerificationCode retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @GetMapping("/paginate/{page}")
  @Override
  public ResponseEntity<Page<EmailVerificationCodeDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable usersSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by("username").descending());

    Page<EmailVerificationCodeDTO> page = this.emailVerificationCodeService.findAll(
            usersSortedByNameDesc)
        .map(this.mapper::toDTO);

    return ResponseEntity.ok(page);
  }
}
