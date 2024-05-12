package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Interaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long>,
    JpaSpecificationExecutor<Interaction> {

  public List<Interaction> findByUserId(Long id);

  public List<Interaction> findByPostId(Long id);

  public List<Interaction> findByInteractionType(Integer interactionType);

  public List<Interaction> findByPostIdAndInteractionType(Long postID, Integer interactionType);

}
