package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Role;
import by.juanjo.jitter.core.repository.RoleRepository;
import by.juanjo.jitter.rest.service.RoleService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceBase<Role, Long, RoleRepository> implements
    RoleService {

  @Autowired
  public RoleServiceImpl(RoleRepository repository) {
    super(repository);
  }
}
