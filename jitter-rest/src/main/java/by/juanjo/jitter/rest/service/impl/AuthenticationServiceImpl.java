package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.dto.auth.LoginRequestDTO;
import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.security.jwt.UserJWTProvider;
import by.juanjo.jitter.rest.service.AuthenticationService;
import by.juanjo.jitter.rest.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private AuthenticationManager authenticationmanager;
  private UserJWTProvider jwtProvider;
  private UserService userService;

  @Autowired
  public AuthenticationServiceImpl(AuthenticationManager authenticationmanager,
      UserJWTProvider jwtProvider, UserService userService) {
    this.authenticationmanager = authenticationmanager;
    this.jwtProvider = jwtProvider;
    this.userService = userService;
  }

  @Override
  public String authenticateAndReturnJwtToken(LoginRequestDTO dto)
      throws AuthenticationException, JsonProcessingException {

    String username = dto.getUsername();
    User user = this.userService.findByUsername(username).orElseThrow(
        () -> new ElementNotFoundException(
            String.format("Couldn't find user with username: %s", username))
    );

    Authentication authentication = this.authenticate(dto);
    this.updateSecurityContext(authentication);

    return this.jwtProvider.issueToken(user);
  }


  private Authentication authenticate(LoginRequestDTO dto) throws AuthenticationException {

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

    return this.authenticationmanager.authenticate(authenticationToken);
  }

  private UserDetails updateSecurityContext(Authentication authentication) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return (UserDetails) authentication.getPrincipal();
  }
}
