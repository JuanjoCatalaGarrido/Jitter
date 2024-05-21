package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
    JpaSpecificationExecutor<User> {

  public Optional<User> findOneByUsername(String username);

  public Optional<User> findOneByEmail(String email);

}
