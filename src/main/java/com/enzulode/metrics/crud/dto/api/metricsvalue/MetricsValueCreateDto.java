package com.enzulode.metrics.crud.dto.api.metricsvalue;

import java.time.Instant;

public record MetricsValueCreateDto(double value, Instant relevantOn) {}
