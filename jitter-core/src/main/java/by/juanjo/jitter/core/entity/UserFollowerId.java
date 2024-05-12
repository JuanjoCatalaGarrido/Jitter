package by.juanjo.jitter.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Embeddable
public @Data class UserFollowerId implements Serializable {

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "follower_id", nullable = false)
  private Long followerId;
}