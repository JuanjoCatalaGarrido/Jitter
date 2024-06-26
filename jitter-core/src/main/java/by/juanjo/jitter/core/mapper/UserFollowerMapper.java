package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserFollowerDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalUserFollowerDTO;
import by.juanjo.jitter.core.entity.UserFollower;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class, UserFollowerIdMapper.class})
public interface UserFollowerMapper {

  UserFollowerMapper INSTANCE = Mappers.getMapper(UserFollowerMapper.class);

  public UserFollowerDTO toDTO(UserFollower entity);

  public MinimalUserFollowerDTO toMinimalDTO(UserFollower entity);

  public UserFollower toEntity(UserFollowerDTO dto);

  public UserFollower toEntity(MinimalUserFollowerDTO dto);
}
