package by.juanjo.jitter.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import lombok.Data;

public @Data class UserPreferenceDTO implements Serializable {

  private Long id;
  private UserSummaryDTO user;
  private Boolean darkMode = false;
  private Boolean telemetry = false;

  @JsonProperty(access = Access.READ_ONLY)
  public Long getId() {
    return this.id;
  }
}
