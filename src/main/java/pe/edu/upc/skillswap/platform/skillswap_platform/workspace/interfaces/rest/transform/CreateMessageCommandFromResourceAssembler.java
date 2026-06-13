package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.CreateMessageCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.CreateMessageResource;

public class CreateMessageCommandFromResourceAssembler {
    public static CreateMessageCommand toCommandFromResource(CreateMessageResource resource) {
        return new CreateMessageCommand(
                resource.content(),
                resource.senderId(),
                resource.sessionId());
    }
}