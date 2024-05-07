package by.juanjo.jitter.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
public @Data class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 45, nullable = false)
  private String username;

  @Column(length = 100, nullable = false)
  private String password;

  @Column(length = 45, nullable = false)
  private String email;

  @Column(name = "profile_image_url", length = 100, nullable = false)
  private String profileImgUrl;

  @Column(name = "verified_at", nullable = true)
  @ColumnDefault("NULL")
  private Timestamp verifiedAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  private Timestamp updatedAt;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private UserPreference userPreferences;

  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
  private Set<Comment> userComments;

  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
  private Set<Post> userPosts;

  @OneToMany(mappedBy = "follower")
  private Set<UserFollower> followers;

  @OneToMany(mappedBy = "user")
  private Set<UserFollower> follows;

  @OneToMany(mappedBy = "user")
  private Set<EmailVerificationCode> verificationCodes;

  @OneToMany(mappedBy = "user")
  private Set<UserPostShare> postShares;

  @OneToMany(mappedBy = "user")
  private Set<Report> reports;

  @OneToMany(mappedBy = "user")
  private Set<Interaction> interactions;

}
