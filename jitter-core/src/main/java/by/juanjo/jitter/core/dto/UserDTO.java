package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalEmailVerificationCodeDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalPostDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalRoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;
import lombok.Data;

public @Data class UserDTO implements Serializable {

  private Long id;
  private String username;
  @JsonIgnore
  private String password;
  private String email;
  private String profileImgUrl;
  private Timestamp verifiedAt;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  private Set<MinimalRoleDTO> roles = Collections.emptySet();
  private UserPreferenceDTO userPreferences;
  private Set<CommentDTO> userComments = Collections.emptySet();
  private Set<MinimalPostDTO> userPosts = Collections.emptySet();
  private Set<UserFollowerDTO> followers = Collections.emptySet();
  private Set<UserFollowerDTO> follows = Collections.emptySet();
  private Set<MinimalEmailVerificationCodeDTO> verificationCodes = Collections.emptySet();
  private Set<UserPostShareDTO> postShares = Collections.emptySet();
  private Set<ReportDTO> reports = Collections.emptySet();
  private Set<InteractionDTO> interactions = Collections.emptySet();


  public Integer getFollowersCount() {
    return this.followers.size();
  }

  public Integer getFollowsCount() {
    return this.follows.size();
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Long getId() {
    return this.id;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getVerifiedAt() {
    return verifiedAt;
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
