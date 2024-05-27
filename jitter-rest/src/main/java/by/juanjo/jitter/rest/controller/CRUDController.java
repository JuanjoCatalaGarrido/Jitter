package by.juanjo.jitter.rest.controller;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * @param <D> tipo de instancia DTO
 * @param <K> tipo de la clave (ej. Long)
 */
public interface CRUDController<D, K> {

  public ResponseEntity<D> create(@NotNull D dto);

  public ResponseEntity<D> read(K id);

  public ResponseEntity<D> update(@NotNull D dto, K id);

  public ResponseEntity<Object> delete(K id);

  public ResponseEntity<List<D>> getAll();

  public ResponseEntity<Page<D>> getAllPaginated(Integer pageNumber);


}
