package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.Units;
import com.enzulode.metrics.crud.dao.repository.UnitsRepository;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UnitsServiceImpl extends AbstractBaseCrudService<Units, Long> implements UnitsService {

  public UnitsServiceImpl(UnitsRepository repository) {
    super(repository);
  }

  @Override
  public Units update(Long id, Units entity) {
    var existingUnits = repository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Item was not found"));

    existingUnits.setTitle(entity.getTitle());
    return this.create(existingUnits);
  }
}
