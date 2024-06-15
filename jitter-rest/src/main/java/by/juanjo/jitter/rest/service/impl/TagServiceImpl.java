package by.juanjo.jitter.rest.service.impl;


import by.juanjo.jitter.core.entity.Tag;
import by.juanjo.jitter.core.entity.Tag_;
import by.juanjo.jitter.core.repository.TagRepository;
import by.juanjo.jitter.rest.service.TagService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceBase<Tag, Long, TagRepository> implements TagService {

  @Autowired
  public TagServiceImpl(TagRepository repository) {
    super(repository);
  }

  @Override
  public List<Tag> serveTagsOrderByPostNum() {
    return this.getRepository().findAll(orderByPostsCountDesc());
  }

  public static Specification<Tag> orderByPostsCountDesc() {
    return (root, query, criteriaBuilder) -> {
      Subquery<Long> postCountSubquery = query.subquery(Long.class);
      Root<Tag> subRoot = postCountSubquery.from(Tag.class);
      Join<Object, Object> postsJoin = subRoot.join(Tag_.ASSOCIATED_POSTS, JoinType.LEFT);

      postCountSubquery.select(criteriaBuilder.count(postsJoin));
      postCountSubquery.groupBy(subRoot.get(Tag_.ID));
      postCountSubquery.where(criteriaBuilder.equal(subRoot.get(Tag_.ID), root.get(Tag_.ID)));

      query.orderBy(criteriaBuilder.desc(postCountSubquery));

      return criteriaBuilder.conjunction();
    };
  }

}
