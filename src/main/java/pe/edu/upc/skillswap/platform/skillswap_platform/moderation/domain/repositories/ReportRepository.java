package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    Report save(Report report);
    Optional<Report> findById(Long id);
    List<Report> findAll();
    List<Report> findByReporterId(Long reporterId);
    List<Report> findByStatus(ReportStatus status);
    void deleteById(Long id);
}