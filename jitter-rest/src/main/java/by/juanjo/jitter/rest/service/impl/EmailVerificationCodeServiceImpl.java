package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.EmailVerificationCode;
import by.juanjo.jitter.core.repository.EmailVerificationCodeRepository;
import by.juanjo.jitter.rest.service.EmailVerificationCodeService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailVerificationCodeServiceImpl extends
    ServiceBase<EmailVerificationCode, Long, EmailVerificationCodeRepository> implements
    EmailVerificationCodeService {

  @Autowired
  public EmailVerificationCodeServiceImpl(EmailVerificationCodeRepository repository) {
    super(repository);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<EmailVerificationCode> findLatestByUserId(Long id) {
    return this.getRepository().findLatestByUserId(id);
  }
}
