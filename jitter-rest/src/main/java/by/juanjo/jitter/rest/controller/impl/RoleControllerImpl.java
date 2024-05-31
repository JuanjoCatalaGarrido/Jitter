package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.RoleDTO;
import by.juanjo.jitter.core.entity.Role;
import by.juanjo.jitter.core.mapper.RoleMapper;
import by.juanjo.jitter.rest.controller.RoleController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.RoleService;
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
@RequestMapping("/api/roles")
public class RoleControllerImpl implements RoleController {

  private RoleService service;
  private RoleMapper mapper;

  @Autowired
  public RoleControllerImpl(RoleService service, RoleMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @ApiResponse(responseCode = "201", description = "Role created successfully", content = {
      @Content(schema = @Schema(implementation = RoleDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided role is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<RoleDTO> create(@RequestBody @NotNull RoleDTO roleDTO) {
    Role newRole = mapper.toEntity(roleDTO);
    Role savedRole = this.service.save(newRole);

    RoleDTO savedRoleDTO = this.mapper.toDTO(savedRole);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedRoleDTO);
  }


  @ApiResponse(responseCode = "404", description = "Role not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Role found", content = {
      @Content(schema = @Schema(implementation = RoleDTO.class))})
  @Parameter(name = "id", description = "The role's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<RoleDTO> read(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<Role> possiblyFoundRole = this.service.findById(id);

    Role role = possiblyFoundRole.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find role with id: %d", id)));

    RoleDTO roleDTO = this.mapper.toDTO(role);
    return ResponseEntity.ok(roleDTO);
  }

  @ApiResponse(responseCode = "404", description = "Role not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "Role updated successfully", content = {
      @Content(schema = @Schema(implementation = RoleDTO.class))})
  @Parameter(name = "id", description = "The role's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<RoleDTO> update(@RequestBody @NotNull RoleDTO newRoleDTO,
      @PathVariable Long id) {
    Optional<Role> possiblyFoundRole = this.service.findById(id);
    if (!possiblyFoundRole.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Role roleToBeUpdated = possiblyFoundRole.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newRoleDTO, roleToBeUpdated, IGNORE_ATTRIB);

    Role updatedRole = this.service.save(roleToBeUpdated);

    RoleDTO roleDTO = this.mapper.toDTO(updatedRole);
    return ResponseEntity.status(HttpStatus.CREATED).body(roleDTO);
  }

  @ApiResponse(responseCode = "404", description = "Role not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "Role deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The role's id", example = "1", required = true)
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (!this.service.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    this.service.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Roles retrieved successfully", content = {
      @Content(schema = @Schema(implementation = RoleDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<RoleDTO>> getAll() {
    Iterable<Role> roles = this.service.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<RoleDTO> allRolesDTO = StreamSupport.stream(roles.spliterator(), CONCURRENT_FLAG)
        .map(this.mapper::toDTO).toList();

    return ResponseEntity.ok(allRolesDTO);
  }

  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<RoleDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable rolesSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by("rolename").descending());

    Page<RoleDTO> page = this.service.findAll(rolesSortedByNameDesc)
        .map(this.mapper::toDTO);

    return ResponseEntity.ok(page);
  }


}
