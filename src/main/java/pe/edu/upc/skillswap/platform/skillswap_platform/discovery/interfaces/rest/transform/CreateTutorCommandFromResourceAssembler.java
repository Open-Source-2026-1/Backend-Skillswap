package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.CreateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.resources.CreateTutorResource;

public class CreateTutorCommandFromResourceAssembler {
    public static CreateTutorCommand toCommandFromResource(CreateTutorResource resource) {
        return new CreateTutorCommand(
                resource.name(),
                resource.university(),
                resource.bio(),
                resource.rating(),
                resource.skills(),
                resource.available(),
                resource.avatarUrl(),
                resource.specialty(),
                resource.portfolioUrl(),
                resource.yearsExperience());
    }
}