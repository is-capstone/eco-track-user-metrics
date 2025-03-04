package com.enzulode.metrics.crud.mapper;

import com.enzulode.metrics.crud.dao.entity.Units;
import com.enzulode.metrics.crud.dto.api.units.UnitsCreateDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsReadDto;
import com.enzulode.metrics.crud.dto.api.units.UnitsUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnitsMapper {

  @Mappings({
      @Mapping(target = "id", ignore = true), // ignore id. should not be set on creation
      @Mapping(source = "title", target = "title")
  })
  Units fromCreateDtoToEntity(UnitsCreateDto dto);

  @Mappings({
      @Mapping(target = "id", source = "id"), // id should be set
      @Mapping(source = "dto.title", target = "title")
  })
  Units fromUpdateDtoToEntity(UnitsUpdateDto dto, Long id);

  @Mappings({
      @Mapping(source = "id", target = "id"),
      @Mapping(source = "title", target = "title")
  })
  UnitsReadDto fromEntityToReadDto(Units entity);
}
