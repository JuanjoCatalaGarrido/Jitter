package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalPostDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalUserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

public @Data class InteractionDTO implements Serializable {

  private Long id;
  private MinimalUserDTO user;
  private MinimalPostDTO post;
  private Integer interactionType;
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
