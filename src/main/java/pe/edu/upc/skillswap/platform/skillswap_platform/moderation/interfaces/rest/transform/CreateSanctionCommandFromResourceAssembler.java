package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.CreateSanctionResource;

public class CreateSanctionCommandFromResourceAssembler {
    public static CreateSanctionCommand toCommandFromResource(CreateSanctionResource resource) {
        return new CreateSanctionCommand(
                resource.userId(),
                resource.type(),
                resource.reason(),
                resource.reportId(),
                resource.expiresAt());
    }
}