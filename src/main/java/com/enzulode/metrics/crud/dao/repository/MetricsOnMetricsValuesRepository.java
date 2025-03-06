package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.MetricsOnMetricsValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsOnMetricsValuesRepository extends JpaRepository<MetricsOnMetricsValues, Long> {

  @Modifying
  @Query("DELETE FROM MetricsOnMetricsValues momv WHERE momv.metrics.id = :metricsId")
  void deleteAllByMetricsId(@Param("metricsId") Long metricsId);

  @Modifying
  @Query("DELETE FROM MetricsOnMetricsValues momv WHERE momv.value.id = :valueId")
  void deleteAllByValueId(@Param("valueId") Long valueId);
}
