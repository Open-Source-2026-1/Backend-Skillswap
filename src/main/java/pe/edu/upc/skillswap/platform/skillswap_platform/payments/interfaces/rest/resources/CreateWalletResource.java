package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

public record CreateWalletResource(
        Long tutorId,
        String currency,
        String bankName,
        String accountNumber) {
}