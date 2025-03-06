package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsValueRepository extends JpaRepository<MetricsValue, Long> {

  @Modifying
  @Query(value = "WITH values_for_deletion AS (delete from eco_track_metrics_on_metrics_values momv WHERE metrics_id = ?1 returning momv.value_id) DELETE FROM eco_track_metrics_value etmv WHERE etmv.id IN (select value_id from values_for_deletion)", nativeQuery = true)
  void deleteByMetricsId(Long metricId);

  @Modifying
  @Query(value = "WITH values_for_deletion AS (delete from eco_track_metrics_on_metrics_values momv WHERE value_id = ?1 returning momv.value_id) DELETE FROM eco_track_metrics_value etmv WHERE etmv.id IN (select value_id from values_for_deletion)", nativeQuery = true)
  void deleteByValueId(Long valueId);
}
