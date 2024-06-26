package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalEmailVerificationCodeDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalPostDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalRoleDTO;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import lombok.Data;

public @Data class UserDetailsDTO implements Serializable {

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

}
