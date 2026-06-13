package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.*;

import java.util.Optional;

public interface TutoringSessionCommandService {
    Optional<TutoringSession> handle(CreateTutoringSessionCommand command);
    Optional<TutoringSession> handle(UpdateTutoringSessionStatusCommand command);
    void handle(DeleteTutoringSessionCommand command);
}