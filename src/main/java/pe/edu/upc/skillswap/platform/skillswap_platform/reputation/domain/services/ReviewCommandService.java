package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

public interface ReviewCommandService {
    Result<Long, ApplicationError> handle(CreateReviewCommand command);
}