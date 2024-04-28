package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.UserDTO;
import by.juanjo.jitter.core.dto.UserDetailsDTO;
import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserPreferenceMapper.class, CommentMapper.class,
    PostMapper.class, UserFollowerMapper.class, EmailVerificationCodeMapper.class,
    UserPostShareMapper.class, ReportMapper.class, InteractionMapper.class})
public interface UserMapper {

  public static UserMapper getInstance() {
    return Mappers.getMapper(UserMapper.class);
  }

  public UserDTO toUserDTO(User entity);

  public UserSummaryDTO toUserSummaryDTO(User entity);

  public UserDetailsDTO toUserDetailsDTO(User entity);

  public User toEntity(UserDTO dto);

  public User toEntity(UserSummaryDTO dto);

  public User toEntity(UserDetailsDTO dto);

}
