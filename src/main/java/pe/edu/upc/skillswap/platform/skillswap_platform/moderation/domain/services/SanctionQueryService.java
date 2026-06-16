package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface SanctionQueryService {
  List<Sanction> handle(GetAllSanctionsQuery query);
  Optional<Sanction> handle(GetSanctionByIdQuery query);
  List<Sanction> handle(GetSanctionsByReportIdQuery query);
  List<Sanction> handle(GetSanctionsByUserQuery query);
}