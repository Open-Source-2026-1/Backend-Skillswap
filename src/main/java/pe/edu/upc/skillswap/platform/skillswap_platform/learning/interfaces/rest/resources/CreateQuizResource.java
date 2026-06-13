package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources;

import java.util.List;

public record CreateQuizResource(
        String title,
        String course,
        Long createdBy,
        Long tutorId,
        List<String> questions) {
}