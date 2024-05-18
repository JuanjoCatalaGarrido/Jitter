package by.juanjo.jitter.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user_preferences")
public @Data class UserPreference implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  @MapsId
  private User user;

  @Column(name = "dark_mode", columnDefinition = "TINYINT")
  @ColumnDefault("0")
  @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
  private Boolean darkMode = false;

  @Column(columnDefinition = "TINYINT")
  @ColumnDefault("0")
  @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
  private Boolean telemetry = false;
}
