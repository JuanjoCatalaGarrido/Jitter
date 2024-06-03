package by.juanjo.jitter.rest.config;

import by.juanjo.jitter.rest.security.filter.JwtAuthFilter;
import by.juanjo.jitter.rest.security.jwt.JWTAuthenticationEntryPoint;
import by.juanjo.jitter.rest.service.impl.UserDetailsServiceImpl;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public @Data class SecurityConfig {

  private UserDetailsServiceImpl userDetailsService;
  private JwtAuthFilter jwtAuthFilter;
  private JWTAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter,
      JWTAuthenticationEntryPoint authenticationEntryPoint) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthFilter = jwtAuthFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/index.html").permitAll()
            .requestMatchers("/favicon.ico").permitAll()
            .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
            .requestMatchers(new AntPathRequestMatcher("/doc/swagger-ui/**"),
                new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(config ->
            config.frameOptions(FrameOptionsConfig::sameOrigin)
        )
        .exceptionHandling(
            customizer -> customizer.authenticationEntryPoint(authenticationEntryPoint))
        .csrf(AbstractHttpConfigurer::disable)
        .cors(config -> config.configurationSource(corsConfiguration()))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfiguration() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.addAllowedOrigin("http://localhost:3010");
    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "OPTIONS"));
    corsConfig.addAllowedHeader("Authorization");
    corsConfig.addAllowedHeader("Content-Type");
    corsConfig.addAllowedHeader("Accept");
    corsConfig.addAllowedHeader("X-Requested-With");
    corsConfig.addAllowedHeader("Origin");
    corsConfig.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(this.userDetailsService);
    authenticationProvider.setPasswordEncoder(this.passwordEncoder());

    ProviderManager providerManager = new ProviderManager(authenticationProvider);
    providerManager.setEraseCredentialsAfterAuthentication(true);
    return providerManager;
  }

}
