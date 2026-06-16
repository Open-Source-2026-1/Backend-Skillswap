package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

public record UpdateSanctionResource(String type, String description, int durationDays) {
}