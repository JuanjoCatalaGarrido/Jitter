package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserDTO;
import by.juanjo.jitter.core.dto.UserDetailsDTO;
import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.core.dto.auth.RegisterRequestDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalUserDTO;
import by.juanjo.jitter.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserPreferenceMapper.class, CommentMapper.class,
    PostMapper.class, UserFollowerMapper.class, EmailVerificationCodeMapper.class,
    UserPostShareMapper.class, ReportMapper.class, InteractionMapper.class,
    RoleMapper.class})

public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


  @Mapping(target = "followersCount", expression = "java(entity.getFollowers() != null ? entity.getFollowers().size() : 0)")
  @Mapping(target = "followsCount", expression = "java(entity.getFollows() != null ? entity.getFollows().size() : 0)")
  public UserSummaryDTO toUserSummaryDTO(User entity);

  public UserDetailsDTO toUserDetailsDTO(User entity);

  public MinimalUserDTO toMinimalUserDTO(User entity);

  public RegisterRequestDTO toRegisterRequestDTO(User entity);

  public UserDTO toDTO(User entity);


  public User toEntity(UserSummaryDTO dto);

  public User toEntity(UserDetailsDTO dto);

  public User toEntity(MinimalUserDTO dto);

  public User toEntity(RegisterRequestDTO dto);

  public User toEntity(UserDTO dto);

}
