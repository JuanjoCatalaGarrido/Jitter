package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.entity.UserPreference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import lombok.Data;

public @Data class UserDTO implements Serializable {

  private Long id;
  private UserDetailsDTO owner;
  private String body;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @JsonProperty(access = Access.READ_ONLY)
  private UserPreference userPreferences;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<CommentDTO> userComments;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<PostSummaryDTO> userPosts;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<UserFollowerDTO> followers;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<UserFollowerDTO> follows;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<EmailVerificationCodeDTO> verificationCodes;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<UserPostShareDTO> postShares;

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
