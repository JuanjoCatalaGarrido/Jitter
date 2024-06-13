package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.core.dto.auth.LoginRequestDTO;
import by.juanjo.jitter.core.dto.auth.RegisterRequestDTO;
import by.juanjo.jitter.core.entity.EmailVerificationCode;
import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.mapper.UserMapper;
import by.juanjo.jitter.rest.controller.AuthenticationController;
import by.juanjo.jitter.rest.service.AuthenticationService;
import by.juanjo.jitter.rest.service.EmailSenderService;
import by.juanjo.jitter.rest.service.EmailVerificationCodeService;
import by.juanjo.jitter.rest.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthenticationControllerImpl implements AuthenticationController {

  private AuthenticationService authenticationService;
  private UserService userService;
  private EmailVerificationCodeService emailVerificationCodeService;
  private EmailSenderService emailSenderService;

  private UserMapper userMapper;
  private PasswordEncoder passwordEncoder;
  private Random random = new Random();

  @Autowired
  public AuthenticationControllerImpl(AuthenticationService authenticationService,
      UserService userService, EmailVerificationCodeService emailVerificationCodeService,
      EmailSenderService emailSenderService, UserMapper userMapper,
      PasswordEncoder passwordEncoder) {
    this.authenticationService = authenticationService;
    this.userService = userService;
    this.emailVerificationCodeService = emailVerificationCodeService;
    this.emailSenderService = emailSenderService;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @NotNull LoginRequestDTO dto) {

    String token;
    try {
      token = this.authenticationService.authenticateAndReturnJwtToken(dto);
    } catch (Exception e) {
      return this.createErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    return this.createSuccessLoginResponse(token);

  }

  private ResponseEntity<Object> createErrorResponse(Exception e, HttpStatus status) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", false);
    errorResponse.put("message", e.getMessage());
    return new ResponseEntity<>(errorResponse, status);
  }

  private ResponseEntity<Object> createSuccessLoginResponse(String token) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", true);
    errorResponse.put("token", token);
    return new ResponseEntity<>(errorResponse, HttpStatus.OK);
  }


  @ApiResponse(responseCode = "201", description = "User created successfully", content = {
      @Content(schema = @Schema(implementation = UserSummaryDTO.class))})
  @ApiResponse(responseCode = "400", description = "Provided user is null", content = {
      @Content(schema = @Schema())})
  @PostMapping("/register")
  @Override
  public ResponseEntity<Object> register(@RequestBody @NotNull RegisterRequestDTO dto) {
    User userToRegister = this.userMapper.toEntity(dto);
    userToRegister.setPassword(this.passwordEncoder.encode(userToRegister.getPassword()));

    User savedUser = this.userService.save(userToRegister);

    String verificationCode = String.format("%04d", this.random.nextInt(10000));

    EmailVerificationCode verificationCodeEntity = new EmailVerificationCode();
    verificationCodeEntity.setUser(savedUser);
    verificationCodeEntity.setCode(verificationCode);

    this.emailVerificationCodeService.save(verificationCodeEntity);

    try {
      this.emailSenderService.sendEmailVerificationCode(savedUser.getEmail(), savedUser.getId(),
          verificationCode);
    } catch (MessagingException e) {
      Map<String, String> response = new HashMap<>();
      response.put("error", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    UserSummaryDTO savedUserDTO = this.userMapper.toUserSummaryDTO(savedUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
  }
}
