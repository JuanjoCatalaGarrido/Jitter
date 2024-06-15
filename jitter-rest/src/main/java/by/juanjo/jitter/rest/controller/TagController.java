package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.TagDTO;
import by.juanjo.jitter.rest.controller.generic.CRUDController;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface TagController extends CRUDController<TagDTO, Long> {

  public ResponseEntity<List<TagDTO>> serveTagsOrderedByPostsCount();

}
