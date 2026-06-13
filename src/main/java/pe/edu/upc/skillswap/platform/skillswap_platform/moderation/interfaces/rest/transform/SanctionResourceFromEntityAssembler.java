package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.SanctionResource;

public class SanctionResourceFromEntityAssembler {
    public static SanctionResource toResourceFromEntity(Sanction entity) {
        return new SanctionResource(
                entity.getId(),
                entity.getUserId(),
                entity.getType(),
                entity.getReason(),
                entity.getReportId(),
                entity.getAppliedAt(),
                entity.getExpiresAt());
    }
}