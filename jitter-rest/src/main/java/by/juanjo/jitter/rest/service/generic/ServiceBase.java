package by.juanjo.jitter.rest.service.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param <E> The entity type
 * @param <K> The primary key type
 * @param <R> The repository type
 */
public @Data class ServiceBase<
    E extends Serializable,
    K extends Serializable,
    R extends JpaRepository<E, K> & JpaSpecificationExecutor<E>> implements Service<E, K> {

  private final R repository;

  public ServiceBase(R repository) {
    this.repository = repository;
  }

  @Override
  @Transactional(readOnly = true)
  public Iterable<E> findAll() {
    return this.repository.findAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Page<E> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<E> findById(K id) {
    return this.repository.findById(id);
  }

  @Override
  @Transactional(readOnly = false)
  public E save(E save) {
    return this.repository.save(save);
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteById(K id) {
    this.repository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<E> findOne(Specification<E> spec) {
    return this.repository.findOne(spec);
  }

  @Override
  @Transactional(readOnly = true)
  public List<E> findAll(Specification<E> spec) {
    return this.repository.findAll(spec);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<E> findAll(Specification<E> spec, Pageable pageable) {
    return this.repository.findAll(spec, pageable);
  }

  @Transactional(readOnly = true)
  @Override
  public List<E> findAll(Specification<E> spec, Sort sort) {
    return List.of();
  }
}
