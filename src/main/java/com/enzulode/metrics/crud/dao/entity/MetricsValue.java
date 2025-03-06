package com.enzulode.metrics.crud.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eco_track_metrics_value")
public class MetricsValue extends BaseEntity<Long> {

  @Column(nullable = false)
  private double value;

  @Column(nullable = false)
  private Instant relevantOn;

//  @ManyToMany(mappedBy = "values", cascade = { CascadeType.PERSIST })
//  @Getter(AccessLevel.NONE)
//  private Set<Metrics> metrics = new HashSet<>();

  @OneToMany(mappedBy = "value")
  @Getter(AccessLevel.NONE)
//  @Setter(AccessLevel.NONE)
  @JsonIgnore
  private Set<MetricsOnMetricsValues> metricsOnValues = new HashSet<>();

  public MetricsValue(Long id, double value, Instant relevantOn) {
    super(id);
    this.value = value;
    this.relevantOn = relevantOn;
  }

  public MetricsValue(double value, Instant relevantOn) {
    super();
    this.value = value;
    this.relevantOn = relevantOn;
  }

  public static MetricsValue fromCsv(String[] csvLine) {
    return new MetricsValue(
        Double.parseDouble(csvLine[0]),
        Instant.parse(csvLine[1])
    );
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    MetricsValue that = (MetricsValue) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
