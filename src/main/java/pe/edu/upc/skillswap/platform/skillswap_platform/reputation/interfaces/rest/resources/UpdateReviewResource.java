package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources;

public record UpdateReviewResource(
        Float rating,
        String comment,
        String tutorReply) {
}