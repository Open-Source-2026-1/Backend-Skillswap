package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.ReportRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.SanctionRepository;

import java.util.Optional;

@Service
public class SanctionCommandServiceImpl implements SanctionCommandService {

  private final SanctionRepository sanctionRepository;
  private final ReportRepository reportRepository;

  public SanctionCommandServiceImpl(SanctionRepository sanctionRepository, ReportRepository reportRepository) {
    this.sanctionRepository = sanctionRepository;
    this.reportRepository = reportRepository;
  }

  @Override
  public Long handle(CreateSanctionCommand command) {
    if (!this.reportRepository.existsById(command.reportId())) {
      throw new IllegalArgumentException("Report with id " + command.reportId() + " does not exist");
    }
    if (command.durationDays() < 0) {
      throw new IllegalArgumentException("Duration days cannot be negative");
    }
    var sanction = new Sanction(command);
    this.sanctionRepository.save(sanction);
    return sanction.getId();
  }

  @Override
  public Optional<Sanction> handle(UpdateSanctionCommand command) {
    if (!this.sanctionRepository.existsById(command.sanctionId())) {
      throw new IllegalArgumentException("Sanction with id " + command.sanctionId() + " does not exist");
    }
    if (command.durationDays() < 0) {
      throw new IllegalArgumentException("Duration days cannot be negative");
    }
    var sanctionToUpdate = this.sanctionRepository.findById(command.sanctionId()).get();
    sanctionToUpdate.updateInformation(command.type(), command.description(), command.durationDays());
    var updatedSanction = this.sanctionRepository.save(sanctionToUpdate);
    return Optional.of(updatedSanction);
  }

  @Override
  public void handle(DeleteSanctionCommand command) {
    if (!this.sanctionRepository.existsById(command.sanctionId())) {
      throw new IllegalArgumentException("Sanction with id " + command.sanctionId() + " does not exist");
    }
    this.sanctionRepository.deleteById(command.sanctionId());
  }
}
