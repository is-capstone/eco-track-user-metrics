package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.Units;
import com.enzulode.metrics.crud.dao.repository.UnitsRepository;
import com.enzulode.metrics.crud.exception.ItemAlreadyExistsException;
import com.enzulode.metrics.crud.exception.ItemNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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

    try {
      existingUnits.setTitle(entity.getTitle());
      return repository.save(existingUnits);
    } catch (DataIntegrityViolationException e) {
      var message = e.getCause().getMessage();

      if (message.contains("violates unique constraint"))
        throw new ItemAlreadyExistsException("Item already exists", e);

      throw e;
    }
  }
}
