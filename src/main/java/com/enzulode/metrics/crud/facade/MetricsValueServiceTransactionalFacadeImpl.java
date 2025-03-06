package com.enzulode.metrics.crud.facade;

import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueCreateDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueReadDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueUpdateDto;
import com.enzulode.metrics.crud.exception.ItemCreationFailedException;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import com.enzulode.metrics.crud.mapper.MetricsValueMapper;
import com.enzulode.metrics.crud.service.MetricsValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricsValueServiceTransactionalFacadeImpl implements MetricsValueServiceFacade {

  private final MetricsValueService delegate;
  private final MetricsValueMapper mapper;

  @Override
  @Transactional
  public MetricsValueReadDto create(MetricsValueCreateDto dto) {
    var newMetricsValue = mapper.fromCreateDtoToEntity(dto);
    return Optional.ofNullable(delegate.create(newMetricsValue))
        .map(mapper::fromEntityToReadDto)
        .orElseThrow(ItemCreationFailedException::new);
  }

  @Override
  public MetricsValueReadDto findById(Long id) {
    return delegate.findById(id)
        .map(mapper::fromEntityToReadDto)
        .orElseThrow(ItemNotFoundException::new);
  }

  @Override
  public Page<MetricsValueReadDto> findAll(Pageable pageable) {
    return delegate.findAll(pageable)
        .map(mapper::fromEntityToReadDto);
  }

  @Override
  @Transactional
  public MetricsValueReadDto update(Long id, MetricsValueUpdateDto dto) {
    var updatingMetricsValue = mapper.fromUpdateDtoToEntity(dto, id);
    return mapper.fromEntityToReadDto(delegate.update(id, updatingMetricsValue));
  }

  @Override
  @Transactional
  public void delete(Long id) {
    delegate.delete(id);
  }

  @Override
  @Transactional
  public void deleteAll(List<Long> ids) {
    delegate.deleteAll(ids);
  }
}
