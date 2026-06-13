package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.CreateDonationCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources.CreateDonationResource;

public class CreateDonationCommandFromResourceAssembler {
    public static CreateDonationCommand toCommandFromResource(CreateDonationResource resource) {
        return new CreateDonationCommand(
                resource.donorId(),
                resource.tutorId(),
                resource.sessionId(),
                resource.amount(),
                resource.commission(),
                resource.currency());
    }
}