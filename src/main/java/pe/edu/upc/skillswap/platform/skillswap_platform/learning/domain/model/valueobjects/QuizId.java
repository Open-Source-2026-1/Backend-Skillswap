package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects;

import java.util.Objects;

public record QuizId(Long value) {

    public QuizId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[QuizId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[QuizId] value must be greater than zero");
        }
    }

    public QuizId() { this(0L); }
}