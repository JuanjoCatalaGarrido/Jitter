package by.juanjo.jitter.core.dto;

import by.juanjo.jitter.core.entity.ReportId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

public @Data class ReportDTO implements Serializable {

  private ReportId id;
  private UserDetailsDTO user;
  private PostSummaryDTO post;
  private Integer importance;
  private String details;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @JsonProperty(access = Access.READ_ONLY)
  public ReportId getId() {
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
