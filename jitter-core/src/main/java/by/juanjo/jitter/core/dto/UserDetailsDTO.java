package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.entity.UserPreference;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;

public @Data class UserDetailsDTO implements Serializable {

  private UserPreference userPreferences;
  private Set<CommentDTO> userComments;
  private Set<PostSummaryDTO> userPosts;
  private Set<UserFollowerDTO> followers;
  private Set<UserFollowerDTO> follows;
  private Set<EmailVerificationCodeDTO> verificationCodes;
  private Set<UserPostShareDTO> postShares;
  private Set<ReportDTO> reports;
  private Set<InteractionDTO> interactions;


}
