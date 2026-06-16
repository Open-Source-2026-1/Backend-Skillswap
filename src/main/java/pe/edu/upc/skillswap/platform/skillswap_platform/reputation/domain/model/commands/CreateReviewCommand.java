package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands;

public record CreateReviewCommand(Long tutorId, Long studentId, Long sessionId, Integer scoreValue, String comment) {}