package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.PostDTO;
import by.juanjo.jitter.core.dto.PostDetailsDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.rest.controller.generic.CRUDController;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PostController extends CRUDController<PostDTO, Long> {

  public ResponseEntity<List<PostDTO>> servePostsByUserId(@NotNull Long userID);

  public ResponseEntity<PostSummaryDTO> servePostSummary(@PathVariable Long id);

  public ResponseEntity<PostDetailsDTO> servePostDetails(Long id);
  
}
