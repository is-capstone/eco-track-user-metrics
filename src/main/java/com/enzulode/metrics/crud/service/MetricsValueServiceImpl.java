package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import com.enzulode.metrics.crud.dao.repository.MetricsValueRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricsValueServiceImpl extends AbstractBaseCrudService<MetricsValue, Long> implements MetricsValueService {

  public MetricsValueServiceImpl(MetricsValueRepository repository) {
    super(repository);
  }

  @Override
  public MetricsValue internalUpdate(Long id, MetricsValue entity) {
    throw new RuntimeException("Operation not supported");
  }
}
