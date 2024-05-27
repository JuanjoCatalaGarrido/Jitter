package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.repository.UserRepository;
import by.juanjo.jitter.rest.service.UserService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceBase<User, Long, UserRepository> implements
    UserService {

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }

  public Optional<User> findByUsername(String username) {
    return this.getRepository().findOneByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return this.getRepository().findOneByEmail(email);
  }
}
