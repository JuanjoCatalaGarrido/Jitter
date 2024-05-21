package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.rest.service.generic.Service;
import java.util.Optional;

public interface UserService extends Service<User, Long> {

  public Optional<User> findByUsername(String username);

  public Optional<User> findByEmail(String email);
}
