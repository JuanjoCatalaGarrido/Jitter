package by.juanjo.jitter.core.dto.minimal;

import by.juanjo.jitter.core.dto.UserFollowerIdDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

public @Data class MinimalUserFollowerDTO implements Serializable {

  private UserFollowerIdDTO id;
  private Timestamp createdAt;

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getCreatedAt() {
    return this.createdAt;
  }
}
