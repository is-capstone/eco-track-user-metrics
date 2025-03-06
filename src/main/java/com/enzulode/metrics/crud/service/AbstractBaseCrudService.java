package com.enzulode.metrics.crud.service;

import com.enzulode.metrics.crud.dao.entity.BaseEntity;
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

      System.out.println();
      System.out.println("ROOOT CAUSE");
      System.out.println((e.getRootCause() != null) ? e.getRootCause().getMessage() : "NO ROOT CAUSE");
      System.out.println();
      if (e.getRootCause() != null) System.out.println("ROOT CAUSE CLASS: " + e.getRootCause().getClass().getName());
      if (e.getRootCause() != null) e.getRootCause().printStackTrace();
      System.out.println("ROOOT CAUSE ENDED");
      System.out.println();

      System.out.println();
      System.out.println("CAUSE");
      System.out.println((e.getCause() != null) ? e.getCause().getMessage() : "CAUSE");
      System.out.println();
      if (e.getCause() != null) System.out.println("CAUSE CLASS: " + e.getCause().getClass().getName());
      if (e.getCause() != null) e.getCause().printStackTrace();
      System.out.println("CAUSE ENDED");
      System.out.println();

      throw e;
    }
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
