package by.juanjo.jitter.rest.service;

import by.juanjo.jitter.core.entity.Tag;
import by.juanjo.jitter.rest.service.generic.Service;
import java.util.List;

public interface TagService extends Service<Tag, Long> {

  public List<Tag> serveTagsOrderByPostNum();

}
