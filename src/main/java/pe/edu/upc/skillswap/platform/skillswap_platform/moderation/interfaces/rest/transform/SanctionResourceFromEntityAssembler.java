package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.SanctionResource;

public class SanctionResourceFromEntityAssembler {
  public static SanctionResource toResourceFromEntity(Sanction entity) {
    return new SanctionResource(entity.getId(), entity.getReportId(), entity.getSanctionedUserId(),
            entity.getType(), entity.getDescription(), entity.getDurationDays(),
            entity.getCreatedAt(), entity.getUpdatedAt());
  }
}
