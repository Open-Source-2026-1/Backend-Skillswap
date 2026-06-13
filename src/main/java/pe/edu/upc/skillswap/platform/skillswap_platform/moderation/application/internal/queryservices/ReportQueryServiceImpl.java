package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.ReportJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportJpaRepository reportJpaRepository;

    public ReportQueryServiceImpl(ReportJpaRepository reportJpaRepository) {
        this.reportJpaRepository = reportJpaRepository;
    }

    @Override
    public List<Report> handle(GetAllReportsQuery query) {
        return reportJpaRepository.findAll();
    }

    @Override
    public Optional<Report> handle(GetReportByIdQuery query) {
        return reportJpaRepository.findById(query.reportId());
    }

    @Override
    public List<Report> handle(GetReportsByReporterIdQuery query) {
        return reportJpaRepository.findByReporterId(query.reporterId());
    }

    @Override
    public List<Report> handle(GetReportsByStatusQuery query) {
        return reportJpaRepository.findByStatus(query.status());
    }
}