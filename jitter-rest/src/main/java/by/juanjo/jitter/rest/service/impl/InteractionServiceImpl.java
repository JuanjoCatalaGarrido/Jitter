package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Interaction;
import by.juanjo.jitter.core.repository.InteractionRepository;
import by.juanjo.jitter.rest.service.InteractionService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl extends
    ServiceBase<Interaction, Long, InteractionRepository> implements InteractionService {

  public InteractionServiceImpl(InteractionRepository repository) {
    super(repository);
  }
}
