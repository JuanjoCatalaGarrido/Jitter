package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.repository.UserRepository;
import lombok.Data;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import by.juanjo.jitter.rest.security.authentication.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public @Data class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findOneByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with username '%s' not found", username)));

    Hibernate.initialize(user.getRoles());

    return new UserDetailsImpl(user);
  }

}

