package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
  List<Report> findByClosedFalse();
  List<Report> findByClosedTrue();
  List<Report> findByReportedUserId_Value(Long reportedUserId);
  boolean existsByReporterUserId_ValueAndReportedUserId_ValueAndStatus(Long reporterUserId, Long reportedUserId, String status);
}
