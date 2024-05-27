package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.auth.LoginRequestDTO;
import by.juanjo.jitter.core.dto.auth.RegisterRequestDTO;
import by.juanjo.jitter.rest.controller.AuthenticationController;
import by.juanjo.jitter.rest.service.AuthenticationService;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @Autowired
  public AuthenticationControllerImpl(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
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


  @Override
  @PostMapping("/register")
  public ResponseEntity<> register(@RequestBody @NotNull RegisterRequestDTO dto) {
    return null;
  }
}
