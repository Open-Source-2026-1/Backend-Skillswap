package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

public record UpdateReportResource(String reason, String status, boolean closed) {
}