package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;

public record UpdateReportStatusCommand(
        Long reportId,
        ReportStatus status) {
}