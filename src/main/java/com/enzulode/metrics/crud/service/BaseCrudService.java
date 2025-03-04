package com.enzulode.metrics.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseCrudService<T, ID> {

  T create(T entity);
  List<T> createAll(List<T> entities);

  Optional<T> findById(ID id);
  Page<T> findAll(Pageable pageable);

  T update(T entities);

  void delete(ID id);
  void deleteAll(List<ID> ids);
}
