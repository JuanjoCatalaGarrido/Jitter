package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Interaction;
import by.juanjo.jitter.core.entity.InteractionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, InteractionId>,
    JpaSpecificationExecutor<Interaction> {

}
