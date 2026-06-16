package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetAllTutorsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetTutorByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

import java.util.List;

public interface TutorQueryService {
    Result<List<Tutor>, ApplicationError> handle(GetAllTutorsQuery query);
    Result<Tutor, ApplicationError> handle(GetTutorByIdQuery query);
}