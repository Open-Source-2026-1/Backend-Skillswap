package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TutorQueryService {
    List<Tutor> handle(GetAllTutorsQuery query);
    Optional<Tutor> handle(GetTutorByIdQuery query);
    List<Tutor> handle(GetTutorsByAvailabilityQuery query);
    List<Tutor> handle(GetTutorsByUniversityQuery query);
    List<Tutor> handle(GetTutorsBySpecialtyQuery query);
}