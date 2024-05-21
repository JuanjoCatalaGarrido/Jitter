package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,
    JpaSpecificationExecutor<Role> {

  Optional<Role> findOneByName(String name);
}
