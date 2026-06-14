package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources;

public record CreateReviewResource(Long tutorId, Long studentId, Long sessionId, Integer scoreValue, String comment) {}