package by.juanjo.jitter.rest.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EntityScan("by.juanjo.jitter.core.entity")
@ComponentScan("by.juanjo.jitter.core.mapper")
@EnableJpaRepositories(basePackages = {"by.juanjo.jitter.core.repository"})
public class SpringConfig {

  @Value("${spring.mail.password}")
  private String smtpPassword = "";

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("mail.smtp2go.com");
    mailSender.setPort(2525);

    mailSender.setUsername("foropoly");
    mailSender.setPassword(this.smtpPassword);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");
    props.put("mail.from", "jcatgar1709@g.educaand.es");

    return mailSender;
  }
}
