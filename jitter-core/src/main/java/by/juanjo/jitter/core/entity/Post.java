package by.juanjo.jitter.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "posts")
public @Data class Post implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  @Column(length = 500, nullable = false)
  private String body;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "repliedPost", fetch = FetchType.LAZY)
  private Set<Comment> userComments;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
  private Set<Tag> tags;

  @OneToMany(mappedBy = "post")
  private Set<UserPostShare> usersWhoShared;

  @OneToMany(mappedBy = "post")
  private Set<Report> reports;

  @OneToMany(mappedBy = "post")
  private Set<Interaction> interactions;


}
