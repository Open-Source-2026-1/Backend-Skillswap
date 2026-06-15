package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetActiveReportsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetAllReportsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetReportByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetReportsByReportedUserQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetResolvedReportsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.ReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {

  private final ReportRepository reportRepository;

  public ReportQueryServiceImpl(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  @Override
  public List<Report> handle(GetAllReportsQuery query) {
    return this.reportRepository.findAll();
  }

  @Override
  public Optional<Report> handle(GetReportByIdQuery query) {
    return this.reportRepository.findById(query.reportId());
  }

  @Override
  public List<Report> handle(GetActiveReportsQuery query) {
    return this.reportRepository.findByClosedFalse();
  }

  @Override
  public List<Report> handle(GetResolvedReportsQuery query) {
    return this.reportRepository.findByClosedTrue();
  }

  @Override
  public List<Report> handle(GetReportsByReportedUserQuery query) {
    return this.reportRepository.findByReportedUserId(query.reportedUserId());
  }
}
