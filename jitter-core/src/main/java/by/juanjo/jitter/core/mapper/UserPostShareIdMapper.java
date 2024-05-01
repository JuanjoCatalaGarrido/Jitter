package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserPostShareIdDTO;
import by.juanjo.jitter.core.entity.UserPostShareId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserPostShareIdMapper {

  public static UserPostShareIdMapper getInstance() {
    return Mappers.getMapper(UserPostShareIdMapper.class);
  }

  public UserPostShareIdDTO toDTO(UserPostShareId entity);

  public UserPostShareId toEntity(UserPostShareIdDTO dto);
}
