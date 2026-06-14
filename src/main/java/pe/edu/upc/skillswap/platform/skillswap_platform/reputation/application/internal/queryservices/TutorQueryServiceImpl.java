package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetAllTutorsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetTutorByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.TutorQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities.TutorJpaEntity;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.TutorJpaRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorQueryServiceImpl implements TutorQueryService {

    private final TutorJpaRepository tutorRepository;

    public TutorQueryServiceImpl(TutorJpaRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Result<List<Tutor>, ApplicationError> handle(GetAllTutorsQuery query) {
        var tutors = tutorRepository.findAll().stream()
                .map(TutorJpaEntity::toDomain)
                .collect(Collectors.toList());
        return Result.success(tutors);
    }

    @Override
    public Result<Tutor, ApplicationError> handle(GetTutorByIdQuery query) {
        return tutorRepository.findById(query.tutorId())
                .map(TutorJpaEntity::toDomain)
                .map(Result::<Tutor, ApplicationError>success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("Tutor", query.tutorId().toString())));
    }
}