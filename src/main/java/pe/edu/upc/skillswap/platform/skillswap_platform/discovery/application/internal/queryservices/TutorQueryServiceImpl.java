package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services.TutorQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.infrastructure.persistence.jpa.repositories.TutorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TutorQueryServiceImpl implements TutorQueryService {

    private final TutorRepository tutorRepository;

    public TutorQueryServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public List<Tutor> handle(GetAllTutorsQuery query) {
        return this.tutorRepository.findAll();
    }

    @Override
    public Optional<Tutor> handle(GetTutorByIdQuery query) {
        return this.tutorRepository.findById(query.tutorId());
    }

    @Override
    public List<Tutor> handle(GetTutorsByAvailabilityQuery query) {
        return this.tutorRepository.findByAvailable(query.available());
    }

    @Override
    public List<Tutor> handle(GetTutorsByUniversityQuery query) {
        return this.tutorRepository.findByUniversity(query.university());
    }

    @Override
    public List<Tutor> handle(GetTutorsBySpecialtyQuery query) {
        return this.tutorRepository.findBySpecialty(query.specialty());
    }
}