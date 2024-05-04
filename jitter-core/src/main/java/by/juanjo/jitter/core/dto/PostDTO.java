package by.juanjo.jitter.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import lombok.Data;

public @Data class PostDTO implements Serializable {

  private Long id;
  @JsonProperty(access = Access.READ_ONLY)
  private UserSummaryDTO owner;
  private String body;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  @JsonProperty(access = Access.READ_ONLY)
  private Set<CommentDTO> userComments;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<TagDTO> tags;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<UserPostShareDTO> usersWhoShared;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<ReportDTO> reports;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<InteractionDTO> interactions;

  @JsonProperty(access = Access.READ_ONLY)
  public Long getId() {
    return this.id;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getCreatedAt() {
    return this.createdAt;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getUpdatedAt() {
    return this.updatedAt;
  }
}
