package by.juanjo.jitter.core.dto.minimal;

import java.io.Serializable;
import lombok.Data;

public @Data class MinimalUserDTO implements Serializable {

  private Long id;
  private String username;
  private String email;
  private String profileImgUrl;

  public Long getId() {
    return this.id;
  }

}
