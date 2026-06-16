package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.reputation.domain.model.commands.CreateTutorCommand;
import pe.edu.upc.skillswap.platform.reputation.interfaces.rest.resources.CreateTutorResource;

public class CreateTutorCommandFromResourceAssembler {
    public static CreateTutorCommand toCommandFromResource(CreateTutorResource resource) {
        return new CreateTutorCommand(resource.fullName(), resource.university());
    }
}