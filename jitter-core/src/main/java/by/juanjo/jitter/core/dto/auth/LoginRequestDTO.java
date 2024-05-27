package by.juanjo.jitter.core.dto.auth;

import java.io.Serializable;
import lombok.Data;

public @Data class LoginRequestDTO implements Serializable {

  private String username;
  private String password;

}


