package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsValueRepository extends JpaRepository<MetricsValue, Long> {}
