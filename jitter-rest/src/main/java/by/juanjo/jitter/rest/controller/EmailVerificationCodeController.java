package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.EmailVerificationCodeDTO;
import by.juanjo.jitter.rest.controller.generic.CRUDController;
import org.springframework.http.ResponseEntity;

public interface EmailVerificationCodeController extends
    CRUDController<EmailVerificationCodeDTO, Long> {

  public ResponseEntity<EmailVerificationCodeDTO> readFromUserId(Long userId);

}
