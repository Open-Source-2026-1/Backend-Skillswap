package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources.DonationResource;

public class DonationResourceFromEntityAssembler {
    public static DonationResource toResourceFromEntity(Donation entity) {
        return new DonationResource(
                entity.getId(),
                entity.getDonorId(),
                entity.getTutorId(),
                entity.getSessionId(),
                entity.getAmount(),
                entity.getNetAmount(),
                entity.getCommission(),
                entity.getCurrency(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}