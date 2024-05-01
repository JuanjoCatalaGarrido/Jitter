package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.InteractionIdDTO;
import by.juanjo.jitter.core.entity.InteractionId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InteractionIdMapper {

  InteractionIdMapper INSTANCE = Mappers.getMapper(InteractionIdMapper.class);

  public InteractionIdDTO toDTO(InteractionId entity);

  public InteractionId toEntity(InteractionIdDTO dto);
}
