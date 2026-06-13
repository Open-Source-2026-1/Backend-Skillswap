package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services.TutorQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.infrastructure.persistence.jpa.repositories.TutorJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TutorQueryServiceImpl implements TutorQueryService {

    private final TutorJpaRepository tutorJpaRepository;

    public TutorQueryServiceImpl(TutorJpaRepository tutorJpaRepository) {
        this.tutorJpaRepository = tutorJpaRepository;
    }

    @Override
    public List<Tutor> handle(GetAllTutorsQuery query) {
        return tutorJpaRepository.findAll();
    }

    @Override
    public Optional<Tutor> handle(GetTutorByIdQuery query) {
        return tutorJpaRepository.findById(query.tutorId());
    }

    @Override
    public List<Tutor> handle(GetTutorsByAvailabilityQuery query) {
        return tutorJpaRepository.findByAvailable(query.available());
    }

    @Override
    public List<Tutor> handle(GetTutorsByUniversityQuery query) {
        return tutorJpaRepository.findByUniversity(query.university());
    }
}