package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources;

import java.util.Date;

public record ReviewResource(
        Long id,
        Long tutorId,
        Long learnerId,
        String learnerName,
        Float rating,
        String comment,
        Long sessionId,
        String tutorReply,
        Date createdAt,
        Date updatedAt) {
}