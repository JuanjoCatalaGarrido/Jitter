package by.juanjo.jitter.core.dto;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;

public @Data class PostDetailsDTO implements Serializable {

  private Set<CommentDTO> userComments;
  private Set<TagDTO> tags;
  private Set<UserPostShareDTO> usersWhoShared;
  private Set<ReportDTO> reports;
  private Set<InteractionDTO> interactions;

}
