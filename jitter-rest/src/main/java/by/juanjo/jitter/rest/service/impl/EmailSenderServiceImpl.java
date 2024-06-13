package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.rest.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

  private JavaMailSender mailSender;

  @Autowired
  public EmailSenderServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);

    mailSender.send(message);
  }

  @Override
  public void sendEmailVerificationCode(String to, Long userId, String verificationCode)
      throws MessagingException {

    MimeMessage message = this.mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
    helper.setTo(to);
    helper.setSubject("Jitter - Verifica tu cuenta");

    final boolean HTML_FLAG = true;
    final String body = String.format("""
        Haz click en el siguiente enlace para proceder a verificar tu cuenta! <br/>
        <a href='http://localhost:8082/api/auth/verifyAccount/%d?code=%s'>Verifica tu cuenta</a>
        """, userId, verificationCode);
    helper.setText(body, HTML_FLAG);

    mailSender.send(message);
  }

}
