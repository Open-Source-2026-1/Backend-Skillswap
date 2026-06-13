package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;

public record UpdateReportStatusResource(ReportStatus status) {
}