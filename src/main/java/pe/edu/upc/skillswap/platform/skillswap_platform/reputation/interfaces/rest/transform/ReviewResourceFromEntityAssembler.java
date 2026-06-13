package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
                entity.getId(),
                entity.getTutorId().value(),
                entity.getLearnerId().value(),
                entity.getLearnerName(),
                entity.getRating(),
                entity.getComment(),
                entity.getSessionId(),
                entity.getCreatedAt(),
                entity.getTutorReply());
    }
}