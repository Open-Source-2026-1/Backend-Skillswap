package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources;

public record ReviewResource(Long id, Long tutorId, Long studentId, Long sessionId, Integer scoreValue, String comment, String status) {}
