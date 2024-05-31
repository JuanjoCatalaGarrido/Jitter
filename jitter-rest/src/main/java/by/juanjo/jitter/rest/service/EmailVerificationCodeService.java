package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.EmailVerificationCode;
import by.juanjo.jitter.rest.service.generic.Service;
import java.util.List;

public interface EmailVerificationCodeService extends Service<EmailVerificationCode, Long> {

  public List<EmailVerificationCode> findLatestByUserId(Long id);
}
