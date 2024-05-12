package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>,
    JpaSpecificationExecutor<Report> {

  public List<Report> findByUserId(Long id);

  public List<Report> findByPostId(Long id);

  public List<Report> findByImportance(Integer importance);
}
