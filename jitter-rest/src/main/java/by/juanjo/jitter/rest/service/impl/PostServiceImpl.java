package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.core.repository.PostRepository;
import by.juanjo.jitter.rest.service.PostService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl extends ServiceBase<Post, Long, PostRepository> implements
    PostService {

  @Autowired
  public PostServiceImpl(PostRepository repository) {
    super(repository);
  }

  @Transactional(readOnly = true)
  public List<Post> findByOwnerId(Long id) {
    return this.getRepository().findByOwnerId(id);
  }
}
