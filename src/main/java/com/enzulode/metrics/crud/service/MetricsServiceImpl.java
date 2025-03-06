package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.Metrics;
import com.enzulode.metrics.crud.dao.repository.MetricsRepository;
import com.enzulode.metrics.crud.dao.repository.MetricsValueRepository;
import com.enzulode.metrics.crud.exception.ItemAlreadyInUseException;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl extends AbstractBaseCrudService<Metrics, Long> implements MetricsService {

  private final MetricsValueRepository metricsValueRepository;

  public MetricsServiceImpl(
      MetricsRepository repository,
      MetricsValueRepository metricsValueRepository
  ) {
    super(repository);
    this.metricsValueRepository = metricsValueRepository;
  }

  @Override
  protected Metrics internalUpdate(Long id, Metrics entity) {
    var existingMetrics = repository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Item was not found"));

    existingMetrics.setTitle(entity.getTitle());
    existingMetrics.setUnits(entity.getUnits());
    return repository.saveAndFlush(existingMetrics);
  }

  @Override
  public void delete(Long id) {
    try {
      metricsValueRepository.deleteByMetricsId(id);
      metricsValueRepository.flush();
      repository.deleteById(id);
      repository.flush();
    } catch (
        DataIntegrityViolationException e) {
      var cause = e.getRootCause() == null ? e.getCause() : e.getRootCause();
      if (cause != null) {
        var message = cause.getMessage();
        if (message.contains("violates foreign key constraint"))
          throw new ItemAlreadyInUseException("Item is already used by another item", e);
      }
    }
  }
}
