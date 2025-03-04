package com.enzulode.metrics.crud.api;

import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueCreateDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueReadDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueUpdateDto;
import com.enzulode.metrics.crud.facade.MetricsValueServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/values")
@RequiredArgsConstructor
public class MetricsValuesController {

  private final MetricsValueServiceFacade facade;

  @PostMapping("/create")
  public MetricsValueReadDto create(@RequestBody MetricsValueCreateDto dto) {
    return facade.create(dto);
  }

  @GetMapping("/{id}")
  public MetricsValueReadDto findById(@PathVariable Long id) {
    return facade.findById(id);
  }

  @GetMapping
  public Page<MetricsValueReadDto> findAll(Pageable pageable) {
    return facade.findAll(pageable);
  }

  @PutMapping("/{id}/update")
  public MetricsValueReadDto update(@PathVariable Long id, @RequestBody MetricsValueUpdateDto dto) {
    return facade.update(id, dto);
  }

  @DeleteMapping("/{id}/delete")
  public void delete(@PathVariable Long id) {
    facade.delete(id);
  }
}
