package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources;

public record CreateReviewResource(
        Long tutorId,
        Long learnerId,
        String learnerName,
        Float rating,
        String comment,
        Long sessionId,
        String tutorReply) {
}