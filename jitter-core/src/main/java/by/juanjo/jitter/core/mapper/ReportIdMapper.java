package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.ReportIdDTO;
import by.juanjo.jitter.core.entity.ReportId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReportIdMapper {

  ReportIdMapper INSTANCE = Mappers.getMapper(ReportIdMapper.class);

  public ReportIdDTO toDTO(ReportId entity);

  public ReportId toEntity(ReportIdDTO dto);
}
