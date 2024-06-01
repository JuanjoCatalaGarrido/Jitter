package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.ReportDTO;
import by.juanjo.jitter.core.entity.Report;
import by.juanjo.jitter.core.entity.Report_;
import by.juanjo.jitter.core.mapper.ReportMapper;
import by.juanjo.jitter.rest.controller.ReportController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.ReportService;
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
@RequestMapping("/api/reports")
public class ReportControllerImpl implements ReportController {

  private ReportService service;
  private ReportMapper mapper;

  @Autowired
  public ReportControllerImpl(ReportService service, ReportMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @ApiResponse(responseCode = "201", description = "Report created successfully", content = {
      @Content(schema = @Schema(implementation = ReportDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided report is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<ReportDTO> create(@RequestBody @NotNull ReportDTO reportDTO) {
    Report newReport = mapper.toEntity(reportDTO);
    Report savedReport = this.service.save(newReport);

    ReportDTO savedReportDTO = this.mapper.toDTO(savedReport);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedReportDTO);
  }


  @ApiResponse(responseCode = "404", description = "Report not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Report found", content = {
      @Content(schema = @Schema(implementation = ReportDTO.class))})
  @Parameter(name = "id", description = "The report's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<ReportDTO> read(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Report> possiblyFoundReport = this.service.findById(id);

    Report report = possiblyFoundReport.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find report with id: %d", id)));

    ReportDTO reportDTO = this.mapper.toDTO(report);
    return ResponseEntity.ok(reportDTO);
  }

  @ApiResponse(responseCode = "404", description = "Report not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Report updated successfully", content = {
      @Content(schema = @Schema(implementation = ReportDTO.class))})
  @Parameter(name = "id", description = "The report's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<ReportDTO> update(@RequestBody @NotNull ReportDTO newReportDTO,
      @PathVariable Long id) {
    Optional<Report> possiblyFoundReport = this.service.findById(id);
    if (!possiblyFoundReport.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Report reportToBeUpdated = possiblyFoundReport.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newReportDTO, reportToBeUpdated, IGNORE_ATTRIB);

    Report updatedReport = this.service.save(reportToBeUpdated);

    ReportDTO reportDTO = this.mapper.toDTO(updatedReport);
    return ResponseEntity.status(HttpStatus.CREATED).body(reportDTO);
  }

  @ApiResponse(responseCode = "404", description = "Report not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Report deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The report's id", example = "1", required = true)
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (!this.service.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    this.service.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Reports retrieved successfully", content = {
      @Content(schema = @Schema(implementation = ReportDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<ReportDTO>> getAll() {
    Iterable<Report> reports = this.service.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<ReportDTO> allReportsDTO = StreamSupport.stream(reports.spliterator(), CONCURRENT_FLAG)
        .map(this.mapper::toDTO).toList();

    return ResponseEntity.ok(allReportsDTO);
  }

  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<ReportDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable reportsSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by(Report_.CREATED_AT).descending());

    Page<ReportDTO> page = this.service.findAll(reportsSortedByNameDesc)
        .map(this.mapper::toDTO);

    return ResponseEntity.ok(page);
  }

}
