package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.PostDetailsDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.rest.controller.generic.CRUDController;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PostController extends CRUDController<PostSummaryDTO, Long> {

  public ResponseEntity<List<PostSummaryDTO>> servePostsByUserId(@NotNull Long userID);

  public ResponseEntity<PostDetailsDTO> servePostDetails(Long id);
}
