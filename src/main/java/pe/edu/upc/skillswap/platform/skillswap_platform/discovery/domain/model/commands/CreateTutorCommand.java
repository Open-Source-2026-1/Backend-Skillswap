package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands;

import java.util.List;

public record CreateTutorCommand(
        String name,
        String university,
        String bio,
        Double rating,
        List<String> skills,
        Boolean available,
        String avatarUrl,
        String specialty,
        String portfolioUrl,
        Integer yearsExperience) {
}