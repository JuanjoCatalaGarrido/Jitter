package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.UserFollower;
import by.juanjo.jitter.core.entity.UserFollowerId;
import by.juanjo.jitter.rest.service.generic.Service;

public interface UserFollowerService extends Service<UserFollower, UserFollowerId> {

  public UserFollower addFollower(Long userId, Long followerId) throws IllegalArgumentException;

  public void removeFollower(Long userId, Long followerId);
}
