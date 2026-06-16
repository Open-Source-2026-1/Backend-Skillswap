package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands;

public record UpdateReviewCommand(
        Long reviewId,
        Float rating,
        String comment,
        String tutorReply) {
}