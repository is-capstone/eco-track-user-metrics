package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.Units;
import com.enzulode.metrics.crud.dao.repository.UnitsRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitsServiceImpl extends AbstractBaseCrudService<Units, Long> implements UnitsService {

  public UnitsServiceImpl(UnitsRepository repository) {
    super(repository);
  }
}
