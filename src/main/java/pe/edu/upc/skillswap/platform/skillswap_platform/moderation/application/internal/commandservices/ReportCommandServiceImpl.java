package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.*;
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
    this.reportRepository.save(report);
    return report.getId();
  }

  @Override
  public Optional<Report> handle(UpdateReportCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }
    var reportToUpdate = this.reportRepository.findById(command.reportId()).get();
    reportToUpdate.updateInformation(command.reason(), command.status(), command.closed());
    var updatedReport = this.reportRepository.save(reportToUpdate);
    return Optional.of(updatedReport);
  }

  @Override
  public Optional<Report> handle(CloseReportCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }
    var report = this.reportRepository.findById(command.reportId()).get();
    report.close();
    var closedReport = this.reportRepository.save(report);
    return Optional.of(closedReport);
  }

  @Override
  public void handle(DeleteReportCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }
    this.reportRepository.deleteById(command.reportId());
  }
}