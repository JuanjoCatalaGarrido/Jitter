package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalEmailVerificationCodeDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalPostDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalRoleDTO;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;

public @Data class UserDetailsDTO implements Serializable {

  private Set<MinimalRoleDTO> roles;
  private UserPreferenceDTO userPreferences;
  private Set<CommentDTO> userComments;
  private Set<MinimalPostDTO> userPosts;
  private Set<UserFollowerDTO> followers;
  private Set<UserFollowerDTO> follows;
  private Set<MinimalEmailVerificationCodeDTO> verificationCodes;
  private Set<UserPostShareDTO> postShares;
  private Set<ReportDTO> reports;
  private Set<InteractionDTO> interactions;


}
