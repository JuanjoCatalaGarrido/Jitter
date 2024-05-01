package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserPostShareDTO;
import by.juanjo.jitter.core.entity.UserPostShare;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    UserPostShareIdMapper.class, UserMapper.class,
    PostMapper.class})
public interface UserPostShareMapper {

  UserPostShareMapper INSTANCE = Mappers.getMapper(UserPostShareMapper.class);

  public UserPostShareDTO toDTO(UserPostShare entity);

  public UserPostShare toEntity(UserPostShareDTO dto);
}
