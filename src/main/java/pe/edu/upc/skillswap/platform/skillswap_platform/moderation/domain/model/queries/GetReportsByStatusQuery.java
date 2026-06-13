package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;

public record GetReportsByStatusQuery(ReportStatus status) {
}