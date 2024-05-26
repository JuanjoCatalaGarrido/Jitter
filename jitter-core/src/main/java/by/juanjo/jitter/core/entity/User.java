package by.juanjo.jitter.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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

  @Column(length = 45, nullable = false, unique = true)
  private String username;

  @Column(length = 100, nullable = false)
  private String password;

  @Column(length = 45, nullable = false, unique = true)
  private String email;

  @Column(name = "profile_img_url", length = 100, nullable = false)
  private String profileImgUrl;

  @Column(nullable = false)
  private Boolean enabled;

  @Column(name = "verified_at", nullable = true)
  @ColumnDefault("NULL")
  private Timestamp verifiedAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  private Timestamp updatedAt;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Role> roles;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private UserPreference userPreferences;

  @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<UserFollower> followers;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<UserFollower> follows;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<EmailVerificationCode> verificationCodes;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<UserPostShare> postShares;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Report> reports;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Interaction> interactions;

}
