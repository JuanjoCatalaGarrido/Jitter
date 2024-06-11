package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.entity.UserFollower;
import by.juanjo.jitter.core.entity.UserFollowerId;
import by.juanjo.jitter.core.repository.UserFollowerRepository;
import by.juanjo.jitter.core.repository.UserRepository;
import by.juanjo.jitter.rest.service.UserFollowerService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserFollowerServiceImpl extends
    ServiceBase<UserFollower, UserFollowerId, UserFollowerRepository> implements
    UserFollowerService {

  private final UserRepository userRepository;
  private final UserFollowerRepository userFollowerRepository;

  @Autowired
  public UserFollowerServiceImpl(UserFollowerRepository repository, UserRepository userRepository,
      UserFollowerRepository userFollowerRepository) {
    super(repository);
    this.userRepository = userRepository;
    this.userFollowerRepository = userFollowerRepository;
  }

  @Transactional(readOnly = false)
  public UserFollower addFollower(Long userId, Long followerId) throws IllegalArgumentException {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    User follower = userRepository.findById(followerId)
        .orElseThrow(() -> new IllegalArgumentException("follower not found"));

    UserFollowerId userFollowerId = new UserFollowerId();
    userFollowerId.setUserId(userId);
    userFollowerId.setFollowerId(followerId);

    UserFollower userFollower = new UserFollower();
    userFollower.setId(userFollowerId);
    userFollower.setUser(user);
    userFollower.setFollower(follower);

    return userFollowerRepository.save(userFollower);

  }

  @Transactional(readOnly = false)
  public void removeFollower(Long userId, Long followerId) {
    UserFollowerId userFollowerId = new UserFollowerId();
    userFollowerId.setUserId(userId);
    userFollowerId.setFollowerId(followerId);

    UserFollower userFollower = userFollowerRepository.findById(userFollowerId)
        .orElseThrow(() -> new IllegalArgumentException("Follow relationship not found"));

    userFollowerRepository.delete(userFollower);
  }
}
