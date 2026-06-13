package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services.TutorCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.infrastructure.persistence.jpa.repositories.TutorJpaRepository;

import java.util.Optional;

@Service
public class TutorCommandServiceImpl implements TutorCommandService {

    private final TutorJpaRepository tutorJpaRepository;

    public TutorCommandServiceImpl(TutorJpaRepository tutorJpaRepository) {
        this.tutorJpaRepository = tutorJpaRepository;
    }

    @Override
    public Optional<Tutor> handle(CreateTutorCommand command) {
        var tutor = new Tutor(
                command.name(),
                command.university(),
                command.bio(),
                command.rating(),
                command.skills(),
                command.available(),
                command.avatarUrl(),
                command.specialty(),
                command.portfolioUrl(),
                command.yearsExperience());
        var savedTutor = tutorJpaRepository.save(tutor);
        return Optional.of(savedTutor);
    }

    @Override
    public Optional<Tutor> handle(UpdateTutorCommand command) {
        var optionalTutor = tutorJpaRepository.findById(command.tutorId());
        if (optionalTutor.isEmpty()) return Optional.empty();
        var tutor = optionalTutor.get();
        tutor.setName(command.name());
        tutor.setUniversity(command.university());
        tutor.setBio(command.bio());
        tutor.setRating(command.rating());
        tutor.setSkills(command.skills());
        tutor.setAvailable(command.available());
        tutor.setAvatarUrl(command.avatarUrl());
        tutor.setSpecialty(command.specialty());
        tutor.setPortfolioUrl(command.portfolioUrl());
        tutor.setYearsExperience(command.yearsExperience());
        var updatedTutor = tutorJpaRepository.save(tutor);
        return Optional.of(updatedTutor);
    }

    @Override
    public void handle(DeleteTutorCommand command) {
        tutorJpaRepository.deleteById(command.tutorId());
    }
}