package com.enzulode.metrics.crud.facade;

import com.enzulode.metrics.crud.dto.api.units.UnitsCreateDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsReadDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UnitsServiceFacade {

  UnitsReadDto create(UnitsCreateDto dto);

  UnitsReadDto findById(Long id);
  Page<UnitsReadDto> findAll(Pageable pageable);

  UnitsReadDto update(Long id, UnitsUpdateDto dto);

  void delete(Long id);
  void deleteAll(List<Long> ids);

}
