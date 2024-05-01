package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserFollowerDTO;
import by.juanjo.jitter.core.entity.UserFollower;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    UserFollowerIdMapper.class, UserMapper.class})
public interface UserFollowerMapper {

  UserFollowerMapper INSTANCE = Mappers.getMapper(UserFollowerMapper.class);

  public UserFollowerDTO toDTO(UserFollower entity);

  public UserFollower toEntity(UserFollowerDTO dto);
}
