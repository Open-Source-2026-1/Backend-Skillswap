package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources.WalletResource;

public class WalletResourceFromEntityAssembler {
    public static WalletResource toResourceFromEntity(Wallet entity) {
        return new WalletResource(
                entity.getId(),
                entity.getTutorId(),
                entity.getBalance(),
                entity.getCurrency(),
                entity.getBankName(),
                entity.getAccountNumber(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}