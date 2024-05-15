package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.rest.service.generic.Service;
import java.util.List;

public interface PostService extends Service<Post, Long> {

  public List<Post> findByOwnerId(Long id);
}
