package com.enzulode.metrics.crud.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractBaseCrudService<T, ID> implements BaseCrudService<T, ID> {

  protected final JpaRepository<T, ID> repository;

  public T create(T entity) {
    return repository.save(entity);
  }
  public List<T> createAll(List<T> entities) {
    return repository.saveAll(entities);
  }

  public Optional<T> findById(ID id) {
    return repository.findById(id);
  }
  public Page<T> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public T update(T entity) {
    return repository.save(entity);
  }

  public void delete(ID id) {
    repository.deleteById(id);
  }
  public void deleteAll(List<ID> ids) {
    repository.deleteAllById(ids);
  }
}
