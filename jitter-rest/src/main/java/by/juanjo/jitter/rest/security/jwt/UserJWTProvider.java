package by.juanjo.jitter.rest.security.jwt;

import by.juanjo.jitter.core.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.Date;

public interface UserJWTProvider {

  public String issueToken(User user) throws JsonProcessingException;

  public Jws<Claims> retrieve(String token) throws JsonProcessingException;

  public String retrieveIssuer(String token) throws JsonProcessingException;

  public String retrieveSubject(String token) throws JsonProcessingException;

  public Date retrieveIssueDate(String token) throws JsonProcessingException;

  public Date retrieveExpiration(String token) throws JsonProcessingException;

  public boolean isValid(String token);
}
