package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands;

public record CreateReviewCommand(
        Long tutorId,
        Long learnerId,
        String learnerName,
        Float rating,
        String comment,
        Long sessionId,
        String tutorReply) {
}