package by.juanjo.jitter.rest.security.filter;

import by.juanjo.jitter.rest.exception.InvalidJwtTokenException;
import by.juanjo.jitter.rest.exception.UnauthorisedException;
import by.juanjo.jitter.rest.security.jwt.UserJWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public @Data class JwtAuthFilter extends OncePerRequestFilter {

  private UserJWTProvider jwtProvider;
  private UserDetailsService userDetailsService;
  private Set<String> whiteList = Set.of();

  private static Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

  @Autowired
  public JwtAuthFilter(UserJWTProvider jwtProvider, UserDetailsService userDetailsService,
      @Value("${jwt.filter.ignorePaths}") String[] ignorePaths) {
    this.jwtProvider = jwtProvider;
    this.userDetailsService = userDetailsService;
    this.whiteList = Set.of(ignorePaths);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    boolean status = this.whiteList.stream()
        .anyMatch(url -> request.getServletPath().matches(url));
    if (status) {
      logger.debug("{} NOT FILTERED", request.getServletPath());
    }
    return status;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    if (this.authHeaderIsInvalid(authHeader)) {
      logger.debug("Request to {} blocked. Invalid auth header", request.getServletPath());
      throw new UnauthorisedException("Unauthorised");
    }

    String token = authHeader.substring(7);
    if (!this.jwtProvider.isValid(token)) {
      logger.debug("Request to {} blocked. Invalid token", request.getServletPath());
      throw new InvalidJwtTokenException();
    }

    String username = this.jwtProvider.retrieveSubject(token);
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }


  private boolean authHeaderIsInvalid(String authHeader) {
    return (authHeader == null || !authHeader.startsWith("Bearer "));
  }
}
