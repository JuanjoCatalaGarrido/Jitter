package by.juanjo.jitter.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import lombok.Data;

public @Data class PostDTO implements Serializable {

  private Long id;
  private UserSummaryDTO owner;
  private String body;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Set<CommentDTO> userComments;
  private Set<TagDTO> tags;
  private Set<UserPostShareDTO> usersWhoShared;
  private Set<ReportDTO> reports;
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
