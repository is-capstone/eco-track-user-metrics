package com.enzulode.metrics.crud.mapper;

import com.enzulode.metrics.crud.dao.entity.MetricsValue;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueCreateDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueReadDto;
import com.enzulode.metrics.crud.dto.api.metricsvalue.MetricsValueUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MetricsValueMapper {

  @Mappings({
      @Mapping(target = "id", ignore = true), // ignore id. should not be set on creation
      @Mapping(source = "value", target = "value"),
      @Mapping(source = "relevantOn", target = "relevantOn"),
      @Mapping(target = "metricsOnValues", ignore = true)
  })
  MetricsValue fromCreateDtoToEntity(MetricsValueCreateDto dto);

  @Mappings({
      @Mapping(target = "id", source = "id"), // id should be set
      @Mapping(source = "dto.value", target = "value"),
      @Mapping(source = "dto.relevantOn", target = "relevantOn"),
      @Mapping(target = "metricsOnValues", ignore = true)
  })
  MetricsValue fromUpdateDtoToEntity(MetricsValueUpdateDto dto, Long id);

  @Mappings({
      @Mapping(source = "id", target = "id"),
      @Mapping(source = "value", target = "value"),
      @Mapping(source = "relevantOn", target = "relevantOn")
  })
  MetricsValueReadDto fromEntityToReadDto(MetricsValue entity);
}
