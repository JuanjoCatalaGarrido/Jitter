package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
    JpaSpecificationExecutor<User> {

  public List<User> findByUsername(String username);

  public List<User> findByEmail(String email);

}
