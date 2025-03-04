package com.enzulode.metrics.crud.dto.api.metricsvalue;

import java.time.Instant;

public record MetricsValueReadDto(Long id, double value, Instant relevantOn) {}
