package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects;

public record Score(Integer value) {
    public Score {
        if (value == null) {
            throw new IllegalArgumentException("Score value cannot be null");
        }
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Score value must be between 1 and 5");
        }
    }

    public Score() {
        this(1); // Valor por defecto
    }
}