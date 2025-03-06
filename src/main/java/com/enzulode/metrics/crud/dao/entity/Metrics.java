package com.enzulode.metrics.crud.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eco_track_metrics")
public class Metrics extends BaseEntity<Long> {

  @Column(nullable = false, unique = true)
  private String title;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "units_id")
  private Units units;

  @OneToMany(mappedBy = "metrics")
  @Getter(AccessLevel.NONE)
//  @Setter(AccessLevel.NONE)
  private Set<MetricsOnMetricsValues> metrics = new HashSet<>();

//  @ManyToMany(fetch = FetchType.LAZY)
//  @Getter(AccessLevel.PROTECTED)
//  @JoinTable(
//      name = "eco_track_metrics_values_data",
//      joinColumns = @JoinColumn(name = "metrics_id"),
//      inverseJoinColumns = @JoinColumn(name = "metrics_value_id")
//  )
//  private Set<MetricsValue> values = new HashSet<>();

  public Metrics(Long id, String title, Units units) {
    super(id);
    this.title = title;
    this.units = units;
  }

  public Metrics(String title, Units units) {
    super();
    this.title = title;
    this.units = units;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Metrics metrics = (Metrics) o;
    return getId() != null && Objects.equals(getId(), metrics.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
