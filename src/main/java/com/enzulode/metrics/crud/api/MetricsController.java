package com.enzulode.metrics.crud.api;

import com.enzulode.metrics.crud.dto.api.metrics.MetricsCreateDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsReadDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsUpdateDto;
import com.enzulode.metrics.crud.facade.MetricsServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
public class MetricsController {

  private final MetricsServiceFacade facade;

  @PostMapping("/create")
  public MetricsReadDto create(@RequestBody MetricsCreateDto dto) {
    return facade.create(dto);
  }

  @GetMapping("/{id}")
  public MetricsReadDto findById(@PathVariable Long id) {
    return facade.findById(id);
  }

  @GetMapping
  public Page<MetricsReadDto> findAll(Pageable pageable) {
    return facade.findAll(pageable);
  }

  @PutMapping("/{id}/update")
  public MetricsReadDto update(@PathVariable Long id, @RequestBody MetricsUpdateDto dto) {
    return facade.update(id, dto);
  }

  @DeleteMapping("/{id}/delete")
  public void delete(@PathVariable Long id) {
    facade.delete(id);
  }
}
