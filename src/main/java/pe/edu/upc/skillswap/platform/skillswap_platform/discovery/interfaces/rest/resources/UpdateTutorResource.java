package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.resources;

import java.util.List;

public record UpdateTutorResource(
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