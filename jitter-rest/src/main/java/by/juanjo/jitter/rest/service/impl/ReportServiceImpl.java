package by.juanjo.jitter.rest.service.impl;

import by.juanjo.jitter.core.entity.Report;
import by.juanjo.jitter.core.repository.ReportRepository;
import by.juanjo.jitter.rest.service.ReportService;
import by.juanjo.jitter.rest.service.generic.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends ServiceBase<Report, Long, ReportRepository> implements
    ReportService {

  @Autowired
  public ReportServiceImpl(ReportRepository repository) {
    super(repository);
  }
}
