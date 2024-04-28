package by.juanjo.jitter.core.repository.specification;

import by.juanjo.jitter.core.entity.User;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;
import by.juanjo.jitter.core.entity.User_;

public class UserSpecifications {

  private UserSpecifications() {
  }

  public static Specification<User> noCriteria() {
    return Specification.where(null);
  }

  public static Specification<User> byUsername(String username) {
    return (root, query, builder) -> builder.equal(root.get(User_.username), username);
  }

  public static Specification<User> byUsernameLike(String username) {
    return (root, query, builder) -> builder.like(root.get(User_.username), "%" + username + "%");
  }

  public static Specification<User> byEmail(String email) {
    return (root, query, builder) -> builder.equal(root.get(User_.email), email);
  }

  public static Specification<User> byEmailLike(String email) {
    return (root, query, builder) -> builder.like(root.get(User_.email), "%" + email + "%");
  }

  public static Specification<User> createdAt(LocalDate date) {
    return (root, query, builder) -> builder.equal(root.get(User_.createdAt),
        Date.valueOf(date));
  }

  public static Specification<User> createdBefore(LocalDate date) {
    return (root, query, builder) -> builder.lessThan(root.get(User_.createdAt),
        Date.valueOf(date));
  }

  public static Specification<User> createdBetween(LocalDate startingDate, LocalDate endingDate) {
    return (root, query, builder) -> builder.between(root.get(User_.createdAt),
        Date.valueOf(startingDate), Date.valueOf(endingDate));
  }

  public static Specification<User> updatedAt(LocalDate date) {
    return (root, query, builder) -> builder.equal(root.get(User_.updatedAt),
        Date.valueOf(date));
  }

  public static Specification<User> updatedBefore(LocalDate date) {
    return (root, query, builder) -> builder.lessThan(root.get(User_.updatedAt),
        Date.valueOf(date));
  }

  public static Specification<User> updatedBetween(LocalDate startingDate, LocalDate endingDate) {
    return (root, query, builder) -> builder.between(root.get(User_.updatedAt),
        Date.valueOf(startingDate), Date.valueOf(endingDate));
  }

  public static Specification<User> verifiedAt(LocalDate date) {
    return (root, query, builder) -> builder.equal(root.get(User_.verifiedAt),
        Date.valueOf(date));
  }

  public static Specification<User> verifiedBefore(LocalDate date) {
    return (root, query, builder) -> builder.lessThan(root.get(User_.verifiedAt),
        Date.valueOf(date));
  }

  public static Specification<User> verifiedBetween(LocalDate startingDate, LocalDate endingDate) {
    return (root, query, builder) -> builder.between(root.get(User_.verifiedAt),
        Date.valueOf(startingDate), Date.valueOf(endingDate));
  }


}
