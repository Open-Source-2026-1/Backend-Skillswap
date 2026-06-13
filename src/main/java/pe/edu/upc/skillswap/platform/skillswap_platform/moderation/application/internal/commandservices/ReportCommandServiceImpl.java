package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.ReportJpaRepository;

import java.util.Optional;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {

    private final ReportJpaRepository reportJpaRepository;

    public ReportCommandServiceImpl(ReportJpaRepository reportJpaRepository) {
        this.reportJpaRepository = reportJpaRepository;
    }

    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        var report = new Report(
                command.reporterId(),
                command.reporterName(),
                command.reportedUserId(),
                command.reason(),
                command.description(),
                command.sessionId());
        var savedReport = reportJpaRepository.save(report);
        return Optional.of(savedReport);
    }

    @Override
    public Optional<Report> handle(UpdateReportStatusCommand command) {
        var optionalReport = reportJpaRepository.findById(command.reportId());
        if (optionalReport.isEmpty()) return Optional.empty();
        var report = optionalReport.get();
        report.updateStatus(command.status());
        var updatedReport = reportJpaRepository.save(report);
        return Optional.of(updatedReport);
    }

    @Override
    public void handle(DeleteReportCommand command) {
        reportJpaRepository.deleteById(command.reportId());
    }
}