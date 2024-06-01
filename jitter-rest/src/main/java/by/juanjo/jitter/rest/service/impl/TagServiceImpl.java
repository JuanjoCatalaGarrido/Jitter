package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Tag;
import by.juanjo.jitter.core.repository.TagRepository;
import by.juanjo.jitter.rest.service.TagService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceBase<Tag, Long, TagRepository> implements TagService {

  @Autowired
  public TagServiceImpl(TagRepository repository) {
    super(repository);
  }
}
