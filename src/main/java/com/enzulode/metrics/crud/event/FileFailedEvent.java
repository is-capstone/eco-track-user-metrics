package com.enzulode.metrics.crud.event;

public record FileFailedEvent(String objName, String onBehalfOf, String reason) {}
