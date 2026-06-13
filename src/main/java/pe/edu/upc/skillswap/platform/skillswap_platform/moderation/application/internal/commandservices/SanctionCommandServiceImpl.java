package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.SanctionJpaRepository;

import java.util.Optional;

@Service
public class SanctionCommandServiceImpl implements SanctionCommandService {

    private final SanctionJpaRepository sanctionJpaRepository;

    public SanctionCommandServiceImpl(SanctionJpaRepository sanctionJpaRepository) {
        this.sanctionJpaRepository = sanctionJpaRepository;
    }

    @Override
    public Optional<Sanction> handle(CreateSanctionCommand command) {
        var sanction = new Sanction(
                command.userId(),
                command.type(),
                command.reason(),
                command.reportId(),
                command.expiresAt());
        var savedSanction = sanctionJpaRepository.save(sanction);
        return Optional.of(savedSanction);
    }

    @Override
    public void handle(DeleteSanctionCommand command) {
        sanctionJpaRepository.deleteById(command.sanctionId());
    }
}