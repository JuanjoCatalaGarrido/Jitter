package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.PostDTO;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface FeedController {

  public ResponseEntity<Set<PostDTO>> servePosts(Long userId);

  public ResponseEntity<Page<PostDTO>> servePostsPaginated(Long userId, Integer pageNumber,
      Integer numElements);
}
