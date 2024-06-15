package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.repository.PostRepository;
import by.juanjo.jitter.rest.exception.ElementNotFoundException;
import by.juanjo.jitter.rest.service.PostService;
import by.juanjo.jitter.rest.service.UserService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl extends ServiceBase<Post, Long, PostRepository> implements
    PostService {

  private UserService userService;

  public PostServiceImpl(PostRepository repository, UserService userService) {
    super(repository);
    this.userService = userService;
  }

  @Transactional(readOnly = true)
  public List<Post> findByOwnerId(Long id) {
    return this.getRepository().findByOwnerId(id);
  }


  @Override
  public Post save(Post save) {
    User owner = this.userService.findById(save.getOwner().getId())
        .orElseThrow(() -> new ElementNotFoundException("Incorrect user "));
    save.setOwner(owner);

    return this.getRepository().save(save);

  }

}
