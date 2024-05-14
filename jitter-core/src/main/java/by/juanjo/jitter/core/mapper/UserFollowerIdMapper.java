package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserFollowerIdDTO;
import by.juanjo.jitter.core.entity.UserFollowerId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserFollowerIdMapper {

  UserFollowerIdMapper INSTANCE = Mappers.getMapper(UserFollowerIdMapper.class);

  public UserFollowerIdDTO toDTO(UserFollowerId entity);

  public UserFollowerId toEntity(UserFollowerIdDTO dto);
}
