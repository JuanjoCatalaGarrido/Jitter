package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserPostShareIdDTO;
import by.juanjo.jitter.core.entity.UserPostShareId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserPostShareIdMapper {

  UserPostShareIdMapper INSTANCE = Mappers.getMapper(UserPostShareIdMapper.class);

  public UserPostShareIdDTO toDTO(UserPostShareId entity);

  public UserPostShareId toEntity(UserPostShareIdDTO dto);
}
