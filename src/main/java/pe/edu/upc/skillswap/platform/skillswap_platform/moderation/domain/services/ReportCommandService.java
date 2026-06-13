package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.*;

import java.util.Optional;

public interface ReportCommandService {
    Optional<Report> handle(CreateReportCommand command);
    Optional<Report> handle(UpdateReportStatusCommand command);
    void handle(DeleteReportCommand command);
}