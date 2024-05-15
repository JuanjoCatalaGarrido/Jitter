package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.rest.service.generic.Service;
import java.util.List;

public interface UserService extends Service<User, Long> {

  public List<User> findByUsername(String username);

  public List<User> findByEmail(String email);
}
