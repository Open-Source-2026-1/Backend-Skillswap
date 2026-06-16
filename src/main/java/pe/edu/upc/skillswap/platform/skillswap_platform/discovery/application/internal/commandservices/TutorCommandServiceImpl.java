package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services.TutorCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.infrastructure.persistence.jpa.repositories.TutorRepository;

import java.util.Optional;

@Service
public class TutorCommandServiceImpl implements TutorCommandService {

    private final TutorRepository tutorRepository;

    public TutorCommandServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Long handle(CreateTutorCommand command) {
        if (this.tutorRepository.existsByNameAndUniversity(command.name(), command.university())) {
            throw new IllegalArgumentException("A tutor with the same name and university already exists");
        }
        var tutor = new Tutor(command);
        this.tutorRepository.save(tutor);
        return tutor.getId();
    }

    @Override
    public Optional<Tutor> handle(UpdateTutorCommand command) {
        if (!this.tutorRepository.existsById(command.tutorId())) {
            throw new IllegalArgumentException("Tutor with id " + command.tutorId() + " does not exist");
        }
        var tutorToUpdate = this.tutorRepository.findById(command.tutorId()).get();
        tutorToUpdate.updateInformation(command);
        var updatedTutor = this.tutorRepository.save(tutorToUpdate);
        return Optional.of(updatedTutor);
    }

    @Override
    public void handle(DeleteTutorCommand command) {
        if (!this.tutorRepository.existsById(command.tutorId())) {
            throw new IllegalArgumentException("Tutor with id " + command.tutorId() + " does not exist");
        }
        this.tutorRepository.deleteById(command.tutorId());
    }
}