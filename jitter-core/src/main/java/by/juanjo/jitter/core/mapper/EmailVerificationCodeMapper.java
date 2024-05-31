package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.EmailVerificationCodeDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalEmailVerificationCodeDTO;
import by.juanjo.jitter.core.entity.EmailVerificationCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class})
public interface EmailVerificationCodeMapper {

  EmailVerificationCodeMapper INSTANCE = Mappers.getMapper(EmailVerificationCodeMapper.class);

  public EmailVerificationCodeDTO toDTO(EmailVerificationCode entity);


  public MinimalEmailVerificationCodeDTO toMinimalDTO(EmailVerificationCode entity);


  public EmailVerificationCode toEntity(EmailVerificationCodeDTO dto);

  public EmailVerificationCode toEntity(MinimalEmailVerificationCodeDTO dto);

}
