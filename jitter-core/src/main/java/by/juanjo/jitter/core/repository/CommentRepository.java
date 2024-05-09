package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>,
    JpaSpecificationExecutor<Comment> {

  public List<Comment> findByOwnerId(Long id);

  public List<Comment> findByPostId(Long id);

  public List<Comment> findByBody(String body);

  public List<Comment> findByBodyLike(String body);
}
