package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserPreferenceDTO;
import by.juanjo.jitter.core.entity.UserPreference;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    UserMapper.class})
public interface UserPreferenceMapper {

  UserPreferenceMapper INSTANCE = Mappers.getMapper(UserPreferenceMapper.class);

  public UserPreferenceDTO toDTO(UserPreference entity);

  public UserPreference toEntity(UserPreferenceDTO dto);
}
