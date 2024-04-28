package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserPostShareDTO;
import by.juanjo.jitter.core.entity.UserPostShare;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserPostShareIdMapper.class, UserMapper.class,
    PostMapper.class})
public interface UserPostShareMapper {

  public static UserPostShareMapper getInstance() {
    return Mappers.getMapper(UserPostShareMapper.class);
  }

  public UserPostShareDTO toDTO(UserPostShare entity);

  public UserPostShare toEntity(UserPostShareDTO dto);
}
