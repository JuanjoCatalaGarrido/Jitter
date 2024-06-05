package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedService {

  List<Post> servePostToUser(Long userId);

  Page<Post> servePostToUser(Long userId, Pageable pageable);

}
