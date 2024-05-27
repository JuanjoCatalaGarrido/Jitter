package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.UserDetailsDTO;
import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.rest.controller.generic.CRUDController;
import org.springframework.http.ResponseEntity;

public interface UserController extends CRUDController<UserSummaryDTO, Long> {

  public ResponseEntity<UserDetailsDTO> serveUserDetails(Long id);

}
