package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.*;

import java.util.Optional;

public interface TutorCommandService {
    Long handle(CreateTutorCommand command);
    Optional<Tutor> handle(UpdateTutorCommand command);
    void handle(DeleteTutorCommand command);
}