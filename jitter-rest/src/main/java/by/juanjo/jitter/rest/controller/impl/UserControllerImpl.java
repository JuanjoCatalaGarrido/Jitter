package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.UserDTO;
import by.juanjo.jitter.core.dto.UserDetailsDTO;
import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.core.dto.filter.UserFilterDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalUserFollowerDTO;
import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.entity.User_;
import by.juanjo.jitter.core.mapper.UserFollowerMapper;
import by.juanjo.jitter.core.mapper.UserMapper;
import by.juanjo.jitter.rest.controller.UserController;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserControllerImpl implements UserController {

  private final UserService userService;
  private final UserMapper userMapper;
  private final UserFollowerMapper userFollowerMapper;

  @Autowired
  public UserControllerImpl(UserService userService, UserMapper userMapper,
      UserFollowerMapper userFollowerMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.userFollowerMapper = userFollowerMapper;
  }

  @ApiResponse(responseCode = "201", description = "User created successfully", content = {
      @Content(schema = @Schema(implementation = UserDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided user is null", content = {
      @Content(schema = @Schema())})
  @PostMapping
  @Override
  public ResponseEntity<UserDTO> create(@RequestBody @NotNull UserDTO userDTO) {
    User newUser = userMapper.toEntity(userDTO);
    User savedUser = this.userService.save(newUser);

    UserDTO savedUserDTO = this.userMapper.toDTO(savedUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
  }

  @ApiResponse(responseCode = "404", description = "User not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "User found", content = {
      @Content(schema = @Schema(implementation = UserDTO.class))})
  @Parameter(name = "id", description = "The user's id", example = "1", required = true)
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<UserDTO> read(@PathVariable Long id)
      throws ElementNotFoundException {
    Optional<User> possiblyFoundUser = this.userService.findById(id);

    User user = possiblyFoundUser.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find user with id: %d", id)));

    UserDTO userDTO = this.userMapper.toDTO(user);
    return ResponseEntity.ok(userDTO);
  }

  @ApiResponse(responseCode = "404", description = "User not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "201", description = "User updated successfully", content = {
      @Content(schema = @Schema(implementation = UserSummaryDTO.class))})
  @Parameter(name = "id", description = "The user's id", example = "1", required = true)
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> update(@RequestBody @NotNull UserDTO newUserDTO,
      @PathVariable Long id) {
    Optional<User> possiblyFoundUser = this.userService.findById(id);
    if (possiblyFoundUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    User userToBeUpdated = possiblyFoundUser.get();

    final String IGNORE_ATTRIB = "id";
    BeanUtils.copyProperties(newUserDTO, userToBeUpdated, IGNORE_ATTRIB);

    User updatedUser = this.userService.save(userToBeUpdated);

    UserDTO userDTO = this.userMapper.toDTO(updatedUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
  }

  @ApiResponse(responseCode = "404", description = "User not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "User deleted successfully", content = {
      @Content(schema = @Schema())})
  @Parameter(name = "id", description = "The user's id", example = "1", required = true)
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if (this.userService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    this.userService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = {
      @Content(schema = @Schema(implementation = UserSummaryDTO.class))})
  @Override
  @GetMapping
  public ResponseEntity<List<UserDTO>> getAll() {
    Iterable<User> users = this.userService.findAll();

    final boolean CONCURRENT_FLAG = false;
    List<UserDTO> allUsersDTO = StreamSupport.stream(users.spliterator(), CONCURRENT_FLAG)
        .map(this.userMapper::toDTO).toList();

    return ResponseEntity.ok(allUsersDTO);
  }

  @ApiResponse(responseCode = "200", description = "Page retrieved successfully", content = {
      @Content(schema = @Schema(implementation = Page.class))})
  @Parameter(name = "page", description = "The page number", example = "0", required = true)
  @Override
  @GetMapping("/paginate/{page}")
  public ResponseEntity<Page<UserDTO>> getAllPaginated(
      @PathVariable(value = "page") Integer pageNumber) {
    final int ELEMENTS_COUNT = 3;
    Pageable usersSortedByNameDesc = PageRequest.of(pageNumber, ELEMENTS_COUNT,
        Sort.by(User_.USERNAME).descending());

    Page<UserDTO> page = this.userService.findAll(usersSortedByNameDesc)
        .map(this.userMapper::toDTO);

    return ResponseEntity.ok(page);
  }

  @ApiResponse(responseCode = "200", description = "User filtered successfully", content = {
      @Content(schema = @Schema(implementation = UserSummaryDTO.class))})
  @PostMapping("/filter")
  @Override
  public ResponseEntity<List<UserDTO>> filter(@RequestBody @NotNull UserFilterDTO dto) {

    Specification<User> filterCriteria = dto.provideFilterSpecification();

    List<User> result = this.userService.findAll(filterCriteria);
    List<UserDTO> resultAsDTO = result.stream().map(this.userMapper::toDTO)
        .toList();

    return ResponseEntity.ok(resultAsDTO);
  }


  @ApiResponse(responseCode = "404", description = "User not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "User found", content = {
      @Content(schema = @Schema(implementation = UserDetailsDTO.class))})
  @Parameter(name = "id", description = "The users's id", example = "1", required = true)
  @GetMapping("/{id}/details")
  @Override
  public ResponseEntity<UserDetailsDTO> serveUserDetails(
      @PathVariable(value = "id") Long id) throws ElementNotFoundException {
    Optional<User> possiblyFoundUser = this.userService.findById(id);

    User user = possiblyFoundUser.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find user with id: %d", id)));

    UserDetailsDTO userDTO = this.userMapper.toUserDetailsDTO(user);
    return ResponseEntity.ok(userDTO);
  }

  @ApiResponse(responseCode = "404", description = "User not found", content = {
      @Content(schema = @Schema())})
  @ApiResponse(responseCode = "200", description = "User found", content = {
      @Content(schema = @Schema(implementation = UserSummaryDTO.class))})
  @Parameter(name = "id", description = "The users's id", example = "1", required = true)
  @GetMapping("/{id}/summary")
  @Override
  public ResponseEntity<UserSummaryDTO> serveUserSummary(
      @PathVariable(value = "id") Long id) throws ElementNotFoundException {
    Optional<User> possiblyFoundUser = this.userService.findById(id);

    User user = possiblyFoundUser.orElseThrow(
        () -> new ElementNotFoundException(String.format("Couldn't find user with id: %d", id)));

    UserSummaryDTO userDTO = this.userMapper.toUserSummaryDTO(user);
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping("/{id}/followers")
  public ResponseEntity<List<MinimalUserFollowerDTO>> followers(@PathVariable Long id) {
    User user = this.userService.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Not found user with ID: " + id));
    List<MinimalUserFollowerDTO> followers = user.getFollowers().stream()
        .map(this.userFollowerMapper::toMinimalDTO).toList();

    return ResponseEntity.ok(followers);
  }


  @GetMapping("/{id}/follows")
  public ResponseEntity<List<MinimalUserFollowerDTO>> follows(@PathVariable Long id) {
    User user = this.userService.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Not found user with ID: " + id));
    List<MinimalUserFollowerDTO> followers = user.getFollows().stream()
        .map(this.userFollowerMapper::toMinimalDTO).toList();

    return ResponseEntity.ok(followers);
  }


}
