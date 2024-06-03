package by.juanjo.jitter.core.dto.filter;

import by.juanjo.jitter.core.entity.User;
import by.juanjo.jitter.core.entity.User_;
import java.io.Serializable;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

public @Data class UserFilterDTO implements Serializable {

  private Long id = null;
  private String username = "";
  private String email = "";


  public Specification<User> provideFilterSpecification() {
    return (root, query, cb) -> {
      Predicate predicate = cb.and();

      if (this.id != null) {
        predicate =
            cb.and(predicate, cb.equal(root.get(User_.ID), this.id));
      }

      if (StringUtils.isNotBlank(this.username)) {
        predicate =
            cb.and(predicate,
                cb.like(root.get(User_.USERNAME), "%" + this.username.toLowerCase() + "%"));
      }

      if (StringUtils.isNotBlank(this.email)) {
        predicate =
            cb.and(predicate,
                cb.like(root.get(User_.EMAIL), "%" + this.email.toLowerCase() + "%"));
      }

      return predicate;
    };
  }


}
