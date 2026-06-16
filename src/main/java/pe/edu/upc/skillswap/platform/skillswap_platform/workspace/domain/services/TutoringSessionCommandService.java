package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.*;

import java.util.Optional;

public interface TutoringSessionCommandService {
    Long handle(CreateTutoringSessionCommand command);
    Optional<TutoringSession> handle(UpdateTutoringSessionCommand command);
    Optional<TutoringSession> handle(UpdateTutoringSessionStatusCommand command);
}