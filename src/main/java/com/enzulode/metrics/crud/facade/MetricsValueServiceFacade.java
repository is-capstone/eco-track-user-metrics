package com.enzulode.metrics.crud.facade;

import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueCreateDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueReadDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MetricsValueServiceFacade {

  MetricsValueReadDto create(Long id, MetricsValueCreateDto dto);

  MetricsValueReadDto findById(Long id);
  Page<MetricsValueReadDto> findAll(Pageable pageable);

  MetricsValueReadDto update(Long id, MetricsValueUpdateDto dto);

  void delete(Long id);
  void deleteAll(List<Long> ids);
}
