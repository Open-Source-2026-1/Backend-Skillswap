package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.CreateQuizAttemptCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.CreateQuizAttemptResource;

public class CreateQuizAttemptCommandFromResourceAssembler {
    public static CreateQuizAttemptCommand toCommandFromResource(CreateQuizAttemptResource resource) {
        return new CreateQuizAttemptCommand(
                resource.quizId(),
                resource.learnerId());
    }
}