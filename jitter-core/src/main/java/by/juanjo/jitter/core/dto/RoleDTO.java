package by.juanjo.jitter.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

public @Data class RoleDTO implements Serializable {

  private Long id;
  private String name;
  private Timestamp createdAt;

  @JsonProperty(access = Access.READ_ONLY)
  public Long getId() {
    return this.id;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getCreatedAt() {
    return this.createdAt;
  }

}
