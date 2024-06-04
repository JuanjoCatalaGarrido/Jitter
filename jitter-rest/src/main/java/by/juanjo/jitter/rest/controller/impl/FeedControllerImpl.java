package by.juanjo.jitter.rest.controller.impl;

import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.core.mapper.PostMapper;
import by.juanjo.jitter.rest.controller.FeedController;
import by.juanjo.jitter.rest.service.FeedService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
public class FeedControllerImpl implements FeedController {

  private FeedService feedService;
  private PostMapper postMapper;

  @Autowired
  public FeedControllerImpl(FeedService feedService, PostMapper postMapper) {
    this.feedService = feedService;
    this.postMapper = postMapper;
  }

  @Override
  @GetMapping("/posts/forUser/{userId}")
  public ResponseEntity<Set<PostSummaryDTO>> servePosts(@PathVariable Long userId) {
    List<Post> posts = this.feedService.servePostToUser(userId);
    Set<PostSummaryDTO> postsAsDTO = posts.stream().map(this.postMapper::toPostSummaryDTO)
        .collect(Collectors.toUnmodifiableSet());

    return ResponseEntity.ok(postsAsDTO);
  }
}
