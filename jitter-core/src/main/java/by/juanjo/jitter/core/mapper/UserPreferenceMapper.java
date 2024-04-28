package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserPreferenceDTO;
import by.juanjo.jitter.core.entity.UserPreference;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserPreferenceMapper {

  public static UserPreferenceMapper getInstance() {
    return Mappers.getMapper(UserPreferenceMapper.class);
  }

  public UserPreferenceDTO toDTO(UserPreference entity);

  public UserPreference toEntity(UserPreferenceDTO dto);
}
