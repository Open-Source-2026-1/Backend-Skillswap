package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.CreateWalletCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources.CreateWalletResource;

public class CreateWalletCommandFromResourceAssembler {
    public static CreateWalletCommand toCommandFromResource(CreateWalletResource resource) {
        return new CreateWalletCommand(
                resource.tutorId(),
                resource.currency(),
                resource.bankName(),
                resource.accountNumber());
    }
}