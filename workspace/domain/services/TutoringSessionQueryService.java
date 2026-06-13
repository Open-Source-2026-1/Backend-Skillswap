package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TutoringSessionQueryService {
    List<TutoringSession> handle(GetAllTutoringSessionsQuery query);
    Optional<TutoringSession> handle(GetTutoringSessionByIdQuery query);
    List<TutoringSession> handle(GetTutoringSessionsByLearnerIdQuery query);
    List<TutoringSession> handle(GetTutoringSessionsByTutorIdQuery query);
}