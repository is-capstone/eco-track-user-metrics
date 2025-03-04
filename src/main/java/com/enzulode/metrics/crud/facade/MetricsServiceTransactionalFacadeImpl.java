package com.enzulode.metrics.crud.facade;

import com.enzulode.metrics.crud.dto.api.metrics.MetricsCreateDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsReadDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsUpdateDto;
import com.enzulode.metrics.crud.exception.ItemCreationFailedException;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import com.enzulode.metrics.crud.exception.ItemUpdateFailedException;
import com.enzulode.metrics.crud.mapper.MetricsMapper;
import com.enzulode.metrics.crud.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricsServiceTransactionalFacadeImpl implements MetricsServiceFacade {

  private final MetricsService delegate;
  private final MetricsMapper mapper;

  @Override
  @Transactional
  public MetricsReadDto create(MetricsCreateDto dto) {
    var newMetrics = mapper.fromCreateDtoToEntity(dto);
    return Optional.ofNullable(delegate.create(newMetrics))
        .map(mapper::fromEntityToReadDto)
        .orElseThrow(ItemCreationFailedException::new);
  }

  @Override
  public MetricsReadDto findById(Long id) {
    return delegate.findById(id)
        .map(mapper::fromEntityToReadDto)
        .orElseThrow(ItemNotFoundException::new);
  }

  @Override
  public Page<MetricsReadDto> findAll(Pageable pageable) {
    return delegate.findAll(pageable)
        .map(mapper::fromEntityToReadDto);
  }

  @Override
  @Transactional
  public MetricsReadDto update(Long id, MetricsUpdateDto dto) {
    var updatingMetrics = mapper.fromUpdateDtoToEntity(dto, id);
    return Optional.ofNullable(delegate.update(updatingMetrics))
        .map(mapper::fromEntityToReadDto)
        .orElseThrow(ItemUpdateFailedException::new);
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
