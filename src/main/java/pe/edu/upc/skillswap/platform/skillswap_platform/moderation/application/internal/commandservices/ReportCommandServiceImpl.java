package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CloseReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.ReportRepository;

import java.util.Optional;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {

  private final ReportRepository reportRepository;

  public ReportCommandServiceImpl(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  @Override
  public Long handle(CreateReportCommand command) {
    if (command.reporterUserId().equals(command.reportedUserId())) {
      throw new IllegalArgumentException("Reporter and reported user cannot be the same person");
    }

    if (this.reportRepository.existsByReporterUserIdAndReportedUserIdAndStatus(
            command.reporterUserId(), command.reportedUserId(), "pending")) {
      throw new IllegalArgumentException("A pending report already exists between these users");
    }

    var report = new Report(command);
    try {
      this.reportRepository.save(report);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while saving report: " + e.getMessage());
    }
    return report.getId();
  }

  @Override
  public Optional<Report> handle(UpdateReportCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }

    var reportToUpdate = this.reportRepository.findById(command.reportId()).get();
    reportToUpdate.updateInformation(command.reason(), command.status(), command.closed());

    try {
      var updatedReport = this.reportRepository.save(reportToUpdate);
      return Optional.of(updatedReport);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while updating report: " + e.getMessage());
    }
  }

  @Override
  public Optional<Report> handle(CloseReportCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }

    var report = this.reportRepository.findById(command.reportId()).get();
    report.close();

    try {
      var closedReport = this.reportRepository.save(report);
      return Optional.of(closedReport);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while closing report: " + e.getMessage());
    }
  }

  @Override
  public void handle(DeleteReportCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }

    try {
      this.reportRepository.deleteById(command.reportId());
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while deleting report: " + e.getMessage());
    }
  }
}
