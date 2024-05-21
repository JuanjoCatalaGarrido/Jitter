package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.minimal.MinimalRoleDTO;
import by.juanjo.jitter.core.dto.RoleDTO;
import by.juanjo.jitter.core.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class})
public interface RoleMapper {

  RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  public RoleDTO toDTO(Role entity);

  public MinimalRoleDTO toMinimalTagDTO(Role entity);

  public Role toEntity(RoleDTO dto);

  public Role toEntity(MinimalRoleDTO dto);

}
