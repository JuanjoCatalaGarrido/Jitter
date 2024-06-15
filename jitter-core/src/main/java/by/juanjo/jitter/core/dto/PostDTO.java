package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.dto.minimal.MinimalTagDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalUserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;
import lombok.Data;

public @Data class PostDTO implements Serializable {

  private Long id;
  private MinimalUserDTO owner;
  private String body;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  private Set<CommentDTO> userComments = Collections.emptySet();
  private Set<MinimalTagDTO> tags = Collections.emptySet();
  private Set<UserPostShareDTO> usersWhoShared = Collections.emptySet();
  private Set<ReportDTO> reports = Collections.emptySet();
  private Set<InteractionDTO> interactions = Collections.emptySet();
  

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getCreatedAt() {
    return this.createdAt;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getUpdatedAt() {
    return this.updatedAt;
  }
}
