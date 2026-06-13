package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands;

import java.util.List;

public record UpdateQuizCommand(
        Long quizId,
        String title,
        String course,
        List<String> questions) {
}