package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources;

import java.util.Date;
import java.util.List;

public record QuizResource(
        Long id,
        String title,
        String course,
        Long createdBy,
        Long tutorId,
        List<String> questions,
        Date createdAt,
        Date updatedAt) {
}