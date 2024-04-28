package by.juanjo.jitter.core.repository;

import by.juanjo.jitter.core.entity.Report;
import by.juanjo.jitter.core.entity.ReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, ReportId>,
    JpaSpecificationExecutor<Report> {

}
