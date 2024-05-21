package by.juanjo.jitter.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "roles")
public @Data class Role implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 20, nullable = false, unique = true)
  private String name;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
      CascadeType.MERGE})
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<User> associatedUsers;

}
