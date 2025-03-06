package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MetricsValueService extends BaseCrudService<MetricsValue, Long> {

  MetricsValue create(Long metricsId, MetricsValue metricsValue);

  Page<MetricsValue> findAllByMetricsId(Long metricsId, Pageable pageable);
}
