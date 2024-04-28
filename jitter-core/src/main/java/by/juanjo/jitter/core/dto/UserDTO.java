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

  private UserPreference userPreferences;
  private Set<CommentDTO> userComments;
  private Set<PostSummaryDTO> userPosts;
  private Set<UserFollowerDTO> followers;
  private Set<UserFollowerDTO> follows;
  private Set<EmailVerificationCodeDTO> verificationCodes;
  private Set<UserPostShareDTO> postShares;
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
