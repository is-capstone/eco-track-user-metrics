package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import com.enzulode.metrics.crud.dao.repository.MetricsOnMetricsValuesRepository;
import com.enzulode.metrics.crud.dao.repository.MetricsValueRepository;
import com.enzulode.metrics.crud.exception.ItemAlreadyInUseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MetricsValueServiceImpl extends AbstractBaseCrudService<MetricsValue, Long> implements MetricsValueService {

  private final MetricsOnMetricsValuesRepository metricsOnMetricsValuesRepository;

  public MetricsValueServiceImpl(
      MetricsValueRepository repository,
      MetricsOnMetricsValuesRepository metricsOnMetricsValuesRepository
  ) {
    super(repository);
    this.metricsOnMetricsValuesRepository = metricsOnMetricsValuesRepository;
  }

  @Override
  public MetricsValue internalUpdate(Long id, MetricsValue entity) {
    throw new RuntimeException("Operation not supported");
  }

  @Override
  public void delete(Long id) {
    try {
      metricsOnMetricsValuesRepository.deleteAllByValueId(id);
      metricsOnMetricsValuesRepository.flush();
      repository.deleteById(id);
      repository.flush();
    } catch (DataIntegrityViolationException e) {
      var cause = e.getRootCause() == null ? e.getCause() : e.getRootCause();
      if (cause != null) {
        var message = cause.getMessage();
        if (message.contains("violates foreign key constraint"))
          throw new ItemAlreadyInUseException("Item is already used by another item", e);
      }
    }
  }
}
