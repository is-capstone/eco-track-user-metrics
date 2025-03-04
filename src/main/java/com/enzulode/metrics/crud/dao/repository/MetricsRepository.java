package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.Metrics;
import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Long> {

//  @Query("SELECT m.values FROM Metrics m JOIN m.values WHERE m.id = :id")
//  Page<MetricsValue> findMetricsValuesByMetricsId(@Param("id") Long id, Pageable pageable);
}
