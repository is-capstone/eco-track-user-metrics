package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.BaseEntity;
import com.enzulode.metrics.crud.exception.ItemAlreadyExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractBaseCrudService<T extends BaseEntity<ID>, ID> implements BaseCrudService<T, ID> {

  protected final JpaRepository<T, ID> repository;

  public T create(T entity) {
    try {
      return repository.save(entity);
    } catch (DataIntegrityViolationException e) {

      var message = e.getCause().getMessage();
      if (message.contains("violates unique constraint"))
        throw new ItemAlreadyExistsException("Item already exists", e);

      throw e;
    }
  }
  public List<T> createAll(List<T> entities) {
    try {
      return repository.saveAll(entities);
    } catch (DataIntegrityViolationException e) {

      var message = e.getCause().getMessage();
      if (message.contains("violates unique constraint"))
        throw new ItemAlreadyExistsException("Item already exists", e);

      throw e;
    }
  }

  public Optional<T> findById(ID id) {
    return repository.findById(id);
  }
  public Page<T> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public abstract T update(ID id, T entity);

  public void delete(ID id) {
    repository.deleteById(id);
  }
  public void deleteAll(List<ID> ids) {
    repository.deleteAllById(ids);
  }
}
