package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.InteractionDTO;
import by.juanjo.jitter.core.entity.Interaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PostMapper.class,
    InteractionIdMapper.class})
public interface InteractionMapper {

  public static InteractionMapper getInstance() {
    return Mappers.getMapper(InteractionMapper.class);
  }

  public InteractionDTO toDTO(Interaction entity);

  public Interaction toEntity(InteractionDTO dto);
}