package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.dto.auth.LoginRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.AuthenticationException;


public interface AuthenticationService {

  String authenticateAndReturnJwtToken(LoginRequestDTO dto)
      throws AuthenticationException, JsonProcessingException;

}
