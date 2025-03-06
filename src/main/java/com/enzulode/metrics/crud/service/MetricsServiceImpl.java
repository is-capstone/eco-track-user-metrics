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
  public Metrics update(Long id, Metrics entity) {
    var existingMetrics = repository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Item not found"));

    try {
      existingMetrics.setTitle(entity.getTitle());
      existingMetrics.setUnits(entity.getUnits());
      return repository.save(existingMetrics);
    } catch (DataIntegrityViolationException e) {
      var message = e.getCause().getMessage();

      if (message.contains("violates unique constraint"))
        throw new ItemAlreadyExistsException("Item already exists", e);

      throw e;
    }
  }
}
