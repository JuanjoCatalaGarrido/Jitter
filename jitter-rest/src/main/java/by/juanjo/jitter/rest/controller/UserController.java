package by.juanjo.jitter.rest.controller;

import by.juanjo.jitter.core.dto.UserSummaryDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserController {

  public ResponseEntity<UserSummaryDTO> create(UserSummaryDTO userDTO);

  public ResponseEntity<UserSummaryDTO> read(Long id);

  public ResponseEntity<UserSummaryDTO> update(UserSummaryDTO newUserDTO, Long id);

  public ResponseEntity<Object> delete(Long id);

  public ResponseEntity<List<UserSummaryDTO>> getAll();

  public ResponseEntity<Page<UserSummaryDTO>> getAllPaginated(Integer pageNumber);


}
