package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserFollowerIdDTO;
import by.juanjo.jitter.core.entity.UserFollowerId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserFollowerIdMapper {

  public static UserFollowerIdMapper getInstance() {
    return Mappers.getMapper(UserFollowerIdMapper.class);
  }

  public UserFollowerIdDTO toDTO(UserFollowerId entity);

  public UserFollowerId toEntity(UserFollowerIdDTO dto);
}
