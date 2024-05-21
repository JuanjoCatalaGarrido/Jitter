package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserDetailsDTO;
import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserPreferenceMapper.class, CommentMapper.class,
    PostMapper.class, UserFollowerMapper.class, EmailVerificationCodeMapper.class,
    UserPostShareMapper.class, ReportMapper.class, InteractionMapper.class,
    RoleMapper.class})

public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


  public UserSummaryDTO toUserSummaryDTO(User entity);

  public UserDetailsDTO toUserDetailsDTO(User entity);

  public User toEntity(UserSummaryDTO dto);

  public User toEntity(UserDetailsDTO dto);

}
