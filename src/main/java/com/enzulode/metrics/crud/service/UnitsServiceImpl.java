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
      System.out.println("MSG: " + e.getMessage() + "\n\n");
      if (e.getCause() != null) System.out.println("CAUSE: " + e.getCause().getMessage() + "\n\n");
      if (e.getRootCause() != null) System.out.println("ROOT CAUSE: " + e.getRootCause().getMessage() + "\n\n");

      var cause = e.getRootCause() == null ? e.getCause() : e.getRootCause();
      if (cause != null) {
        System.out.println("WE HAVE A CAUSE");
        var message = cause.getMessage();
        if (message.contains("violates unique constraint"))
          throw new ItemAlreadyExistsException("Item already exists", e);
      }

      System.out.println("NO CAUSE :(");
      throw e;
    } catch (Throwable e) {
      System.out.println("MSG: " + e.getMessage() + "\n\n");
      if (e.getCause() != null) System.out.println("CAUSE: " + e.getCause().getClass().getName() + "\n\n");

      var cause = e.getCause();
      if (cause != null) {
        System.out.println("WE HAVE A CAUSE");
        var message = cause.getMessage();
        if (message.contains("violates unique constraint"))
          throw new ItemAlreadyExistsException("Item already exists", e);
      }

      System.out.println("NO CAUSE :(");
      throw e;
    }
  }
}
