package by.juanjo.jitter.rest.controller;


import by.juanjo.jitter.core.dto.auth.LoginRequestDTO;
import by.juanjo.jitter.core.dto.auth.RegisterRequestDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController {

  public ResponseEntity<Object> login(@NotNull LoginRequestDTO dto);

  public ResponseEntity<Object> register(@NotNull RegisterRequestDTO dto);
}
