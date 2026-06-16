package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.TutoringSessionResource;

public class TutoringSessionResourceFromEntityAssembler {
    public static TutoringSessionResource toResourceFromEntity(TutoringSession entity) {
        return new TutoringSessionResource(
                entity.getId(),
                entity.getLearnerId(),
                entity.getTutorId(),
                entity.getTopic(),
                entity.getMessage(),
                entity.getStudentLevel(),
                entity.getStatus(),
                entity.getScheduledAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}