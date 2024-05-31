package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalTagDTO;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;

public @Data class PostDetailsDTO implements Serializable {

  private Set<CommentDTO> userComments;
  private Set<MinimalTagDTO> tags;
  private Set<UserPostShareDTO> usersWhoShared;
  private Set<ReportDTO> reports;
  private Set<InteractionDTO> interactions;

}
