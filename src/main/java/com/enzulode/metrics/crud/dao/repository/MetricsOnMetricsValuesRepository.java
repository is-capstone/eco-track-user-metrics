package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.MetricsOnMetricsValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsOnMetricsValuesRepository extends JpaRepository<MetricsOnMetricsValues, Long> {}
