package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Comment;
import by.juanjo.jitter.core.repository.CommentRepository;
import by.juanjo.jitter.rest.service.CommentService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl extends ServiceBase<Comment, Long, CommentRepository>
    implements CommentService {

  public CommentServiceImpl(CommentRepository repository) {
    super(repository);
  }

}
