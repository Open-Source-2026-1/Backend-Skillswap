package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
                resource.tutorId(),
                resource.learnerId(),
                resource.learnerName(),
                resource.rating(),
                resource.comment(),
                resource.sessionId(),
                resource.tutorReply());
    }
}