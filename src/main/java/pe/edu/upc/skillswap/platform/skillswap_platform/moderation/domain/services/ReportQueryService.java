package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ReportQueryService {
    List<Report> handle(GetAllReportsQuery query);
    Optional<Report> handle(GetReportByIdQuery query);
    List<Report> handle(GetReportsByReporterIdQuery query);
    List<Report> handle(GetReportsByStatusQuery query);
}