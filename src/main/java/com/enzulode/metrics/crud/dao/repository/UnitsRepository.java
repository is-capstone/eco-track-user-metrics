package com.enzulode.metrics.crud.dao.repository;

import com.enzulode.metrics.crud.dao.entity.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Long> {}
