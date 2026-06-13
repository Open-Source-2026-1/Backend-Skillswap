package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.*;

import java.util.Optional;

public interface SanctionCommandService {
    Optional<Sanction> handle(CreateSanctionCommand command);
    void handle(DeleteSanctionCommand command);
}