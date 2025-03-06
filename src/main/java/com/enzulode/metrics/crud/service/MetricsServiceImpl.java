package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.Metrics;
import com.enzulode.metrics.crud.dao.repository.MetricsRepository;
import com.enzulode.metrics.crud.exception.ItemAlreadyExistsException;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl extends AbstractBaseCrudService<Metrics, Long> implements MetricsService {

  public MetricsServiceImpl(MetricsRepository repository) {
    super(repository);
  }

  @Override
  protected Metrics internalUpdate(Long id, Metrics entity) {
    var existingMetrics = repository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Item was not found"));

    existingMetrics.setTitle(entity.getTitle());
    existingMetrics.setUnits(entity.getUnits());
    return repository.saveAndFlush(existingMetrics);
  }
}
