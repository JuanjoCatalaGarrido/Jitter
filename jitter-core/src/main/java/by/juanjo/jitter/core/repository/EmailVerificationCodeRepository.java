package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.EmailVerificationCode;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCode, Long>,
    JpaSpecificationExecutor<EmailVerificationCode> {

  public List<EmailVerificationCode> findByUserId(Long id);

  public Optional<EmailVerificationCode> findFirstByUserIdOrderByCreatedAt(Long id);

  public List<EmailVerificationCode> findByCode(Integer code);
}
