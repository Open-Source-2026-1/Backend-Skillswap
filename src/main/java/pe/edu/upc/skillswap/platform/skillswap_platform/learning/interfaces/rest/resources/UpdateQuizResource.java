package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources;

import java.util.List;

public record UpdateQuizResource(
        String title,
        String course,
        List<String> questions) {
}