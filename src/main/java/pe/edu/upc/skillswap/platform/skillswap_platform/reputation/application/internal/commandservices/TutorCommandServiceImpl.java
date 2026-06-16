package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.CreateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.DeleteTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.UpdateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.TutorCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities.TutorJpaEntity;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.TutorJpaRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

@Service
public class TutorCommandServiceImpl implements TutorCommandService {

    private final TutorJpaRepository tutorRepository;

    public TutorCommandServiceImpl(TutorJpaRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateTutorCommand command) {
        if (tutorRepository.existsByFullName(command.fullName())) {
            return Result.failure(ApplicationError.conflict("Tutor", "A tutor with this full name already exists"));
        }

        var tutorDomain = new Tutor(command.fullName(), command.university());
        var entityToSave = TutorJpaEntity.fromDomain(tutorDomain);
        var savedEntity = tutorRepository.save(entityToSave);

        return Result.success(savedEntity.getId());
    }

    @Override
    public Result<Tutor, ApplicationError> handle(UpdateTutorCommand command) {
        if (tutorRepository.existsByFullNameAndIdIsNot(command.fullName(), command.tutorId())) {
            return Result.failure(ApplicationError.conflict("Tutor", "Another tutor with this full name already exists"));
        }

        var optionalEntity = tutorRepository.findById(command.tutorId());
        if (optionalEntity.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Tutor", command.tutorId().toString()));
        }

        var entity = optionalEntity.get();
        var domain = entity.toDomain();
        domain.updateInformation(command.fullName(), command.university());

        var updatedEntity = TutorJpaEntity.fromDomain(domain);

        tutorRepository.save(updatedEntity);
        return Result.success(updatedEntity.toDomain());
    }

    @Override
    public Result<Boolean, ApplicationError> handle(DeleteTutorCommand command) {
        if (!tutorRepository.existsById(command.tutorId())) {
            return Result.failure(ApplicationError.notFound("Tutor", command.tutorId().toString()));
        }
        tutorRepository.deleteById(command.tutorId());
        return Result.success(true);
    }
}