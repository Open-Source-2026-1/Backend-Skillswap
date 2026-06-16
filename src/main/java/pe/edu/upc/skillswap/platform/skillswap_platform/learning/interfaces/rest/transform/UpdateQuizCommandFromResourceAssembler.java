package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.UpdateQuizCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.UpdateQuizResource;

public class UpdateQuizCommandFromResourceAssembler {
    public static UpdateQuizCommand toCommandFromResource(Long quizId, UpdateQuizResource resource) {
        return new UpdateQuizCommand(
                quizId,
                resource.title(),
                resource.course(),
                resource.questions());
    }
}