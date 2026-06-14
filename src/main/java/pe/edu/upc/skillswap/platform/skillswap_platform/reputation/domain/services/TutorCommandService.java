package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.CreateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.DeleteTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.UpdateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

public interface TutorCommandService {
    Result<Long, ApplicationError> handle(CreateTutorCommand command);
    Result<Tutor, ApplicationError> handle(UpdateTutorCommand command);
    Result<Boolean, ApplicationError> handle(DeleteTutorCommand command);
}