package by.juanjo.jitter.rest.service;

import jakarta.mail.MessagingException;

public interface EmailSenderService {

  public void sendEmail(String to, String subject, String body);

  public void sendEmailVerificationCode(String to, Long userId, String verificationCode)
      throws MessagingException;
}
