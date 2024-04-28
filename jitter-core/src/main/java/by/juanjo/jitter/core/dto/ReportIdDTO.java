package by.juanjo.jitter.core.dto;

import java.io.Serializable;
import lombok.Data;

public @Data class ReportIdDTO implements Serializable {

  private Long userId;
  private Long postId;

}
