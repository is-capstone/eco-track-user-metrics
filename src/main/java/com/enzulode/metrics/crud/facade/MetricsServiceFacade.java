package com.enzulode.metrics.crud.facade;

import com.enzulode.metrics.crud.dto.api.metrics.MetricsCreateDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsReadDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MetricsServiceFacade {

  MetricsReadDto create(MetricsCreateDto dto);

  MetricsReadDto findById(Long id);
  Page<MetricsReadDto> findAll(Pageable pageable);

  MetricsReadDto update(Long id, MetricsUpdateDto dto);

  void delete(Long id);
  void deleteAll(List<Long> ids);

}
