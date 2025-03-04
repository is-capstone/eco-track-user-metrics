package com.enzulode.metrics.crud.dto.api.metricsvalue;

import java.time.Instant;

public record MetricsValueUpdateDto(double value, Instant relevantOn) {}
