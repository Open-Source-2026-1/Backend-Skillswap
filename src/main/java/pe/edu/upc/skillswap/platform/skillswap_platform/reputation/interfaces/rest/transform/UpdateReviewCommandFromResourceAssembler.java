package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.UpdateReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long reviewId, UpdateReviewResource resource) {
        return new UpdateReviewCommand(
                reviewId,
                resource.rating(),
                resource.comment(),
                resource.tutorReply());
    }
}