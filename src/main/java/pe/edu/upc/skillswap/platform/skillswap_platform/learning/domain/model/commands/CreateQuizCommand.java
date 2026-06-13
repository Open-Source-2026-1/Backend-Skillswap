package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands;

import java.util.List;

public record CreateQuizCommand(
        String title,
        String course,
        Long createdBy,
        Long tutorId,
        List<String> questions) {
}