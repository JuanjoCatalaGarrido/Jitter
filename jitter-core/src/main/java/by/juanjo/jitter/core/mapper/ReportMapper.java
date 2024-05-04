package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.ReportDTO;
import by.juanjo.jitter.core.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    ReportIdMapper.class, UserMapper.class,
    PostMapper.class})
public interface ReportMapper {

  ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

  public ReportDTO toDTO(Report entity);

  public Report toEntity(ReportDTO dto);
}
