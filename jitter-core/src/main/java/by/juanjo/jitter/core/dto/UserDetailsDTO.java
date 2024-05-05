package by.juanjo.jitter.core.dto;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;

public @Data class UserDetailsDTO implements Serializable {

  private UserPreferenceDTO userPreferences;
  private Set<CommentDTO> userComments;
  private Set<PostSummaryDTO> userPosts;
  private Set<UserFollowerDTO> followers;
  private Set<UserFollowerDTO> follows;
  private Set<EmailVerificationCodeDTO> verificationCodes;
  private Set<UserPostShareDTO> postShares;
  private Set<ReportDTO> reports;
  private Set<InteractionDTO> interactions;


}
