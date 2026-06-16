package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.UpdateTutoringSessionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.UpdateTutoringSessionResource;

public class UpdateTutoringSessionCommandFromResourceAssembler {
    public static UpdateTutoringSessionCommand toCommandFromResource(Long sessionId, UpdateTutoringSessionResource resource) {
        return new UpdateTutoringSessionCommand(
                sessionId,
                resource.topic(),
                resource.message(),
                resource.studentLevel(),
                resource.scheduledAt());
    }
}