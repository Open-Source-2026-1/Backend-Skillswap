package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.SanctionJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SanctionQueryServiceImpl implements SanctionQueryService {

    private final SanctionJpaRepository sanctionJpaRepository;

    public SanctionQueryServiceImpl(SanctionJpaRepository sanctionJpaRepository) {
        this.sanctionJpaRepository = sanctionJpaRepository;
    }

    @Override
    public List<Sanction> handle(GetAllSanctionsQuery query) {
        return sanctionJpaRepository.findAll();
    }

    @Override
    public Optional<Sanction> handle(GetSanctionByIdQuery query) {
        return sanctionJpaRepository.findById(query.sanctionId());
    }

    @Override
    public List<Sanction> handle(GetSanctionsByUserIdQuery query) {
        return sanctionJpaRepository.findByUserId(query.userId());
    }
}