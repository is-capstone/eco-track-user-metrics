package com.enzulode.metrics.crud.facade;

import com.enzulode.metrics.crud.dto.api.units.UnitsCreateDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsReadDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsUpdateDto;
import com.enzulode.metrics.crud.exception.ItemCreationFailedException;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import com.enzulode.metrics.crud.exception.ItemUpdateFailedException;
import com.enzulode.metrics.crud.mapper.UnitsMapper;
import com.enzulode.metrics.crud.service.UnitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitsServiceTransactionalFacadeImpl implements UnitsServiceFacade {

  private final UnitsService delegate;
  private final UnitsMapper mapper;

  @Override
  @Transactional
  public UnitsReadDto create(UnitsCreateDto dto) {
    var newUnits = mapper.fromCreateDtoToEntity(dto);
    return mapper.fromEntityToReadDto(delegate.create(newUnits));
  }

  @Override
  public UnitsReadDto findById(Long id) {
    return delegate.findById(id)
        .map(mapper::fromEntityToReadDto)
        .orElseThrow(ItemNotFoundException::new);
  }

  @Override
  public Page<UnitsReadDto> findAll(Pageable pageable) {
    return delegate.findAll(pageable)
        .map(mapper::fromEntityToReadDto);
  }

  @Override
  @Transactional
  public UnitsReadDto update(Long id, UnitsUpdateDto dto) {
    var updatingUnits = mapper.fromUpdateDtoToEntity(dto, id);
    return mapper.fromEntityToReadDto(delegate.update(updatingUnits));
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
