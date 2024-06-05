package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalTagDTO;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import lombok.Data;

public @Data class PostDetailsDTO implements Serializable {

  private Set<CommentDTO> userComments = Collections.emptySet();
  private Set<MinimalTagDTO> tags = Collections.emptySet();
  private Set<UserPostShareDTO> usersWhoShared = Collections.emptySet();
  private Set<ReportDTO> reports = Collections.emptySet();
  private Set<InteractionDTO> interactions = Collections.emptySet();

}
