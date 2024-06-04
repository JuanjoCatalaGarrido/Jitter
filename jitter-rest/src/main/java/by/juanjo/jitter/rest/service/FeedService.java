package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.Post;
import java.util.List;

public interface FeedService {

  List<Post> servePostToUser(Long userId);

}
