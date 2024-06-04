package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.PostSummaryDTO;
import java.util.Set;
import org.springframework.http.ResponseEntity;

public interface FeedController {

  public ResponseEntity<Set<PostSummaryDTO>> servePosts(Long userId);

}
