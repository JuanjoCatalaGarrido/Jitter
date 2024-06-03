package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.UserDetailsDTO;
import by.juanjo.jitter.core.dto.UserSummaryDTO;
import by.juanjo.jitter.core.dto.filter.UserFilterDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalUserFollowerDTO;
import by.juanjo.jitter.rest.controller.generic.CRUDController;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserController extends CRUDController<UserSummaryDTO, Long> {

  public ResponseEntity<List<UserSummaryDTO>> filter(@NotNull UserFilterDTO dto);

  public ResponseEntity<UserDetailsDTO> serveUserDetails(Long id);

  public ResponseEntity<List<MinimalUserFollowerDTO>> followers(Long userId);

  public ResponseEntity<Object> addFollower(Long id, Long followerId);

  public ResponseEntity<List<MinimalUserFollowerDTO>> follows(Long id);
}
