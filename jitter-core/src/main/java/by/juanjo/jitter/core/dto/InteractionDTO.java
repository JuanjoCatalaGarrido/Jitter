package by.juanjo.jitter.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

public @Data class InteractionDTO implements Serializable {

  private InteractionIdDTO id;
  @JsonProperty(access = Access.READ_ONLY)
  private UserSummaryDTO user;
  @JsonProperty(access = Access.READ_ONLY)
  private PostSummaryDTO post;
  private Integer interactionType;
  private Timestamp createdAt;

  @JsonProperty(access = Access.READ_ONLY)
  public InteractionIdDTO getId() {
    return this.id;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getCreatedAt() {
    return this.createdAt;
  }

}
