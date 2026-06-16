package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

import java.util.Date;

public record WalletResource(
        Long id,
        Long tutorId,
        Double balance,
        String currency,
        String bankName,
        String accountNumber,
        Date createdAt,
        Date updatedAt) {
}