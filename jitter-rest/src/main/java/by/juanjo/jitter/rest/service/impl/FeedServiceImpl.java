package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Post;
import by.juanjo.jitter.core.entity.Post_;
import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.entity.UserFollower;
import by.juanjo.jitter.core.entity.UserFollower_;
import by.juanjo.jitter.core.entity.User_;
import by.juanjo.jitter.rest.service.FeedService;
import by.juanjo.jitter.rest.service.PostService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {

  private final PostService postService;

  @Autowired
  public FeedServiceImpl(PostService postService) {
    this.postService = postService;
  }

  @Override
  public List<Post> servePostToUser(Long userId) {
    return this.postService.findAll(postsByUserFollowers(userId));
  }

  public static Specification<Post> postsByUserFollowers(Long userId) {
    return (root, query, criteriaBuilder) -> {
      Join<Post, User> postUserJoin = root.join(Post_.OWNER, JoinType.INNER);
      Join<User, UserFollower> userFollowerJoin = postUserJoin.join(User_.FOLLOWERS,
          JoinType.INNER);

      query.orderBy(criteriaBuilder.desc(root.get(Post_.createdAt)));

      return criteriaBuilder.equal(userFollowerJoin.get(UserFollower_.USER).get(User_.ID), userId);
    };
  }
}
