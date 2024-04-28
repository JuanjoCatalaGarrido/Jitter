package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCode, Long>,
    JpaSpecificationExecutor<EmailVerificationCode> {

}
