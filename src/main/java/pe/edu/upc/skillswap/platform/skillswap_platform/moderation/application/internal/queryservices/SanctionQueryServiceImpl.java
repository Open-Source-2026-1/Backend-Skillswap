package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories.SanctionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SanctionQueryServiceImpl implements SanctionQueryService {

  private final SanctionRepository sanctionRepository;

  public SanctionQueryServiceImpl(SanctionRepository sanctionRepository) {
    this.sanctionRepository = sanctionRepository;
  }

  @Override
  public List<Sanction> handle(GetAllSanctionsQuery query) {
    return this.sanctionRepository.findAll();
  }

  @Override
  public Optional<Sanction> handle(GetSanctionByIdQuery query) {
    return this.sanctionRepository.findById(query.sanctionId());
  }

  @Override
  public List<Sanction> handle(GetSanctionsByReportIdQuery query) {
    return this.sanctionRepository.findByReportId(query.reportId());
  }

  @Override
  public List<Sanction> handle(GetSanctionsByUserQuery query) {
    return this.sanctionRepository.findBySanctionedUserId(query.sanctionedUserId());
  }
}