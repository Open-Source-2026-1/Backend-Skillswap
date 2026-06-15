package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CloseReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateReportCommand;

import java.util.Optional;

public interface ReportCommandService {
  Long handle(CreateReportCommand command);
  Optional<Report> handle(UpdateReportCommand command);
  Optional<Report> handle(CloseReportCommand command);
  void handle(DeleteReportCommand command);
}
