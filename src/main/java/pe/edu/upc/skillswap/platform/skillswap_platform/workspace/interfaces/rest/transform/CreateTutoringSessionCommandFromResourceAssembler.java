package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.CreateTutoringSessionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.CreateTutoringSessionResource;

public class CreateTutoringSessionCommandFromResourceAssembler {
    public static CreateTutoringSessionCommand toCommandFromResource(CreateTutoringSessionResource resource) {
        return new CreateTutoringSessionCommand(
                resource.learnerId(),
                resource.tutorId(),
                resource.topic(),
                resource.message(),
                resource.studentLevel(),
                resource.scheduledAt());
    }
}