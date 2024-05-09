package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>,
    JpaSpecificationExecutor<Post> {

  public List<Post> findByOwnerId(Long id);
}
