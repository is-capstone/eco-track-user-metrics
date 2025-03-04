package com.enzulode.metrics.crud.mapper;

import com.enzulode.metrics.crud.dao.entity.Metrics;
import com.enzulode.metrics.crud.dao.entity.Units;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsCreateDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsReadDto;
import com.enzulode.metrics.crud.dto.api.metrics.MetricsUpdateDto;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UnitsMapper.class)
@DecoratedWith(MetricsMapper.MetricsMapperDecorator.class)
public interface MetricsMapper {

  @Mappings({
      @Mapping(target = "id", ignore = true), // ignore id. should not be set on creation
      @Mapping(source = "title", target = "title"),
      @Mapping(target = "units", ignore = true), // ignore units. should be set by decorator
      @Mapping(target = "metrics", ignore = true),
  })
  Metrics fromCreateDtoToEntity(MetricsCreateDto dto);

  @Mappings({
      @Mapping(target = "id", source = "id"), // id should be set
      @Mapping(source = "dto.title", target = "title"),
      @Mapping(target = "units", ignore = true), // ignore units. should be set by decorator
      @Mapping(target = "metrics", ignore = true)
  })
  Metrics fromUpdateDtoToEntity(MetricsUpdateDto dto, Long id);

  @Mappings({
      @Mapping(source = "id", target = "id"),
      @Mapping(source = "title", target = "title"),
      @Mapping(source = "units", target = "units")
  })
  MetricsReadDto fromEntityToReadDto(Metrics entity);


  @Getter @Setter
  abstract class MetricsMapperDecorator implements MetricsMapper {
    @Autowired
    @Qualifier("delegate")
    private MetricsMapper delegate;
    @Autowired
    private JpaRepository<Units, Long> repo;

    @Override
    public Metrics fromCreateDtoToEntity(MetricsCreateDto dto) {
      var metrics = delegate.fromCreateDtoToEntity(dto);
      var units = repo.findById(dto.unitsId()).orElseThrow();
      metrics.setUnits(units);
      return metrics;
    }

    @Override
    public Metrics fromUpdateDtoToEntity(MetricsUpdateDto dto, Long id) {
      var metrics = delegate.fromUpdateDtoToEntity(dto, id);
      var units = repo.findById(dto.unitsId()).orElseThrow();
      metrics.setUnits(units);
      return metrics;
    }
  }
}
