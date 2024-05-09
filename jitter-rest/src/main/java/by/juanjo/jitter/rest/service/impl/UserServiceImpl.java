package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.repository.UserRepository;
import by.juanjo.jitter.rest.service.UserService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceBase<User, Long, UserRepository> implements
    UserService {

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }

  public List<User> findByUsername(String username) {
    return this.getRepository().findByUsername(username);
  }

  public List<User> findByEmail(String email) {
    return this.getRepository().findByEmail(email);
  }
}
