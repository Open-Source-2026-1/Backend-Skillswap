package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

public record CreateSanctionResource(Long reportId, Long sanctionedUserId, String type, String description, int durationDays) {
}
