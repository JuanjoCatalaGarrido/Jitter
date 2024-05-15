package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.InteractionDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalInteractionDTO;
import by.juanjo.jitter.core.entity.Interaction;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class, PostMapper.class})
public interface InteractionMapper {

  InteractionMapper INSTANCE = Mappers.getMapper(InteractionMapper.class);

  public InteractionDTO toDTO(Interaction entity);

  public MinimalInteractionDTO toMinimalInteractionDTO(Interaction entity);

  public Interaction toEntity(InteractionDTO dto);

  public Interaction toEntity(MinimalInteractionDTO dto);
}
