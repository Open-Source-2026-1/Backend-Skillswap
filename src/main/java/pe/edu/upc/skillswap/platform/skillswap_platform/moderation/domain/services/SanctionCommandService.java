package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateSanctionCommand;

import java.util.Optional;

public interface SanctionCommandService {
  Long handle(CreateSanctionCommand command);
  Optional<Sanction> handle(UpdateSanctionCommand command);
  void handle(DeleteSanctionCommand command);
}
