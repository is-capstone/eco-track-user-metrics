package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface MetricsValueRepository extends JpaRepository<MetricsValue, Long> {

  @Modifying
  @Query(value = "WITH values_for_deletion AS (delete from eco_track_metrics_on_metrics_values momv WHERE metrics_id = ?1 returning momv.value_id) DELETE FROM eco_track_metrics_value etmv WHERE etmv.id IN (select value_id from values_for_deletion)", nativeQuery = true)
  void deleteByMetricsId(Long metricId);

  @Modifying
  @Query(value = "WITH values_for_deletion AS (delete from eco_track_metrics_on_metrics_values momv WHERE value_id = ?1 returning momv.value_id) DELETE FROM eco_track_metrics_value etmv WHERE etmv.id IN (select value_id from values_for_deletion)", nativeQuery = true)
  void deleteByValueId(Long valueId);

  @Modifying
  @Query(value = "WITH inserted_metrics_value AS (insert into eco_track_metrics_value as etmv (value, relevant_on) values (?2, ?3) returning etmv.id) INSERT INTO eco_track_metrics_on_metrics_values (metrics_id, value_id) VALUES (?1, (SELECT imv.id FROM inserted_metrics_value imv))", nativeQuery = true)
  void insertValue(Long metricsId, Double value, Instant relevantOn);

  @Query("SELECT mv FROM MetricsValue mv JOIN MetricsOnMetricsValues momv ON mv.id = momv.value.id WHERE momv.metrics.id = :metricsId")
  Page<MetricsValue> findAllByMetricsId(Long metricsId, Pageable pageable);
}
