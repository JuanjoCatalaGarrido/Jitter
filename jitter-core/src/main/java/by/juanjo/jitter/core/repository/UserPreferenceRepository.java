package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long>,
    JpaSpecificationExecutor<UserPreference> {

}