package by.juanjo.jitter.core.dto.minimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import lombok.Data;

public @Data class MinimalUserDTO implements Serializable {

  private Long id;
  private String username;
  private String email;
  private String profileImgUrl;

  @JsonProperty(access = Access.READ_ONLY)
  public Long getId() {
    return this.id;
  }

}
