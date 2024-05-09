package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.UserFollower;
import by.juanjo.jitter.core.entity.UserFollowerId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowerRepository extends
    JpaRepository<UserFollower, UserFollowerId>,
    JpaSpecificationExecutor<UserFollower> {

  public List<UserFollower> findByUserId(Long id);

  public List<UserFollower> findByFollower(Long id);

}
