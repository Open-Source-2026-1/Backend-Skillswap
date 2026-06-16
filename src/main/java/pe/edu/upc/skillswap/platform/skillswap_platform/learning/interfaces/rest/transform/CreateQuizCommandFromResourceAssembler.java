package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.CreateQuizCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.CreateQuizResource;

public class CreateQuizCommandFromResourceAssembler {
    public static CreateQuizCommand toCommandFromResource(CreateQuizResource resource) {
        return new CreateQuizCommand(
                resource.title(),
                resource.course(),
                resource.createdBy(),
                resource.tutorId(),
                resource.questions());
    }
}