package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>,
    JpaSpecificationExecutor<Tag> {

  public List<Tag> findByName(String name);

}
