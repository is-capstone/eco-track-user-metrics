package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import com.enzulode.metrics.crud.dao.repository.MetricsValueRepository;
import com.enzulode.metrics.crud.exception.ItemAlreadyExistsException;
import com.enzulode.metrics.crud.exception.ItemAlreadyInUseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MetricsValueServiceImpl extends AbstractBaseCrudService<MetricsValue, Long> implements MetricsValueService {

  private final MetricsValueRepository metricsValueRepository;

  public MetricsValueServiceImpl(
      MetricsValueRepository repository,
      MetricsValueRepository metricsValueRepository
  ) {
    super(repository);
    this.metricsValueRepository = metricsValueRepository;
  }

  @Override
  public MetricsValue create(Long metricsId, MetricsValue metricsValue) {
    try {
      metricsValueRepository.insertValue(metricsId, metricsValue.getValue(), metricsValue.getRelevantOn());
    } catch (DataIntegrityViolationException e) {
      var cause = e.getRootCause() == null ? e.getCause() : e.getRootCause();
      if (cause != null) {
        var message = cause.getMessage();
        if (message.contains("violates unique constraint"))
          throw new ItemAlreadyExistsException("Item already exists", e);
      }
      throw e;
    }
    return null;
  }

  @Override
  public Page<MetricsValue> findAllByMetricsId(Long metricsId, Pageable pageable) {
    return metricsValueRepository.findAllByMetricsId(metricsId, pageable);
  }

  @Override
  public MetricsValue internalUpdate(Long id, MetricsValue entity) {
    throw new RuntimeException("Operation not supported");
  }

  @Override
  public void delete(Long id) {
    try {
      metricsValueRepository.deleteByValueId(id);
      metricsValueRepository.flush();
    } catch (DataIntegrityViolationException e) {
      var cause = e.getRootCause() == null ? e.getCause() : e.getRootCause();
      if (cause != null) {
        var message = cause.getMessage();
        if (message.contains("violates foreign key constraint"))
          throw new ItemAlreadyInUseException("Item is already used by another item", e);
      }
      throw e;
    }
  }
}
