package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.Metrics;
import com.enzulode.metrics.crud.dao.repository.MetricsRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl extends AbstractBaseCrudService<Metrics, Long> implements MetricsService {

  public MetricsServiceImpl(MetricsRepository repository) {
    super(repository);
  }
}
