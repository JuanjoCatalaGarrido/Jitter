package by.juanjo.jitter.rest.security.jwt.impl;

import by.juanjo.jitter.rest.security.jwt.UserJWTProvider;
import by.juanjo.jitter.rest.security.jwt.exception.SigningSecretNotDefinedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public @Data class UserJWTProviderImpl implements UserJWTProvider {

  private static final Logger logger = LoggerFactory.getLogger(UserJWTProviderImpl.class);

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private String secret;

  private Long expirationTimeInMS;

  private SecretKey signingKey;

  public UserJWTProviderImpl(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.expiration-time:3600000}") Long expirationTimeInMS) {
    if (secret == null || secret.isEmpty()) {
      throw new SigningSecretNotDefinedException(
          "property 'jwt.secret' may be not defined");
    }
    this.secret = secret;
    this.expirationTimeInMS = expirationTimeInMS;
    this.signingKey = this.getSigningKey();
  }

  private SecretKey getSigningKey() {
    byte[] rawSecret = this.secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(rawSecret);
  }

  @Override
  public String issueToken(UserDetails userDetails) throws JsonProcessingException {
    return Jwts.builder()
        .issuer("Jitter")
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + this.expirationTimeInMS))
        .signWith(this.signingKey)
        .compact();
  }

  @Override
  public Jws<Claims> retrieve(String token) throws JsonProcessingException {
    return Jwts.parser().
        verifyWith(this.signingKey).build()
        .parseSignedClaims(token);
  }

  @Override
  public String retrieveIssuer(String token) throws JsonProcessingException {
    return retrieve(token).getPayload().getIssuer();
  }

  @Override
  public String retrieveSubject(String token) throws JsonProcessingException {
    return retrieve(token).getPayload().getSubject();
  }

  @Override
  public Date retrieveIssueDate(String token) throws JsonProcessingException {
    return retrieve(token).getPayload().getIssuedAt();
  }

  @Override
  public Date retrieveExpiration(String token) throws JsonProcessingException {
    return retrieve(token).getPayload().getExpiration();
  }


  @Override
  public boolean isValid(String token) {
    try {
      Jwts.parser().verifyWith(this.signingKey).build().parseSignedClaims(token);
      return true;

    } catch (MalformedJwtException | ExpiredJwtException |
             UnsupportedJwtException | IllegalArgumentException e) {
      logger.error("Invalid Token: '{}', Thrown: {}", token, e.getMessage());
      return false;
    }
  }
}
