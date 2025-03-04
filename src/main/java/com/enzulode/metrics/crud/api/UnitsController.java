package com.enzulode.metrics.crud.api;

import com.enzulode.metrics.crud.dto.api.units.UnitsCreateDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsReadDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsUpdateDto;
import com.enzulode.metrics.crud.facade.UnitsServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/units")
@RequiredArgsConstructor
public class UnitsController {

  private final UnitsServiceFacade facade;

  @PostMapping("/create")
  public UnitsReadDto create(@RequestBody UnitsCreateDto unitsCreateDto) {
    return facade.create(unitsCreateDto);
  }

  @GetMapping("/{id}")
  public UnitsReadDto findById(@PathVariable Long id) {
    return facade.findById(id);
  }

  @GetMapping
  public Page<UnitsReadDto> findAll(Pageable pageable) {
    return facade.findAll(pageable);
  }

  @PutMapping("/{id}/update")
  public UnitsReadDto update(@PathVariable Long id, @RequestBody UnitsUpdateDto unitsUpdateDto) {
    return facade.update(id, unitsUpdateDto);
  }

  @DeleteMapping("/{id}/delete")
  public void delete(@PathVariable Long id) {
    facade.delete(id);
  }
}
