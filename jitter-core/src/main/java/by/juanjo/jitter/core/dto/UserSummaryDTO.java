package by.juanjo.jitter.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

public @Data class UserSummaryDTO implements Serializable {

  private Long id;
  private String username;
  @JsonIgnore
  private String password;
  private String email;
  private String profileImgUrl;
  private Integer followersCount = 0;
  private Integer followsCount = 0;
  private Timestamp verifiedAt;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @JsonProperty(access = Access.READ_ONLY)
  public Long getId() {
    return this.id;
  }

  @JsonProperty(access = Access.READ_ONLY)
  public Timestamp getVerifiedAt() {
    return verifiedAt;
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
