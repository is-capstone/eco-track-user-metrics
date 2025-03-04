package com.enzulode.metrics.crud.dto.api.metrics;

import com.enzulode.metrics.crud.dto.api.units.UnitsReadDto;

public record MetricsReadDto(Long id, String title, UnitsReadDto units) {}
