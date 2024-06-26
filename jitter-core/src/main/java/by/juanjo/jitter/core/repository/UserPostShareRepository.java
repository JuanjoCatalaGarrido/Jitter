package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.UserPostShare;
import by.juanjo.jitter.core.entity.UserPostShareId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostShareRepository extends
    JpaRepository<UserPostShare, UserPostShareId>,
    JpaSpecificationExecutor<UserPostShare> {

  public List<UserPostShare> findByUserId(Long id);

  public List<UserPostShare> findByPostId(Long id);

}
