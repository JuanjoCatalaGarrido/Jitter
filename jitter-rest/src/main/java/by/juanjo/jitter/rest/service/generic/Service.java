package by.juanjo.jitter.rest.service.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param <E> The entity type
 * @param <K> The primary key type
 */
public interface Service<E extends Serializable, K extends Serializable> {

  @Transactional(readOnly = true)
  public Iterable<E> findAll();

  @Transactional(readOnly = true)
  public Page<E> findAll(Pageable pageable);

  @Transactional(readOnly = true)
  public Optional<E> findById(K id);

  @Transactional(readOnly = false)
  public E save(E save);

  @Transactional(readOnly = false)
  public void deleteById(K id);

  @Transactional(readOnly = true)
  public Optional<E> findOne(Specification<E> spec);

  @Transactional(readOnly = true)
  public List<E> findAll(Specification<E> spec);

  @Transactional(readOnly = true)
  public Page<E> findAll(Specification<E> spec, Pageable pageable);

  @Transactional(readOnly = true)
  public List<E> findAll(Specification<E> spec, Sort sort);


}
