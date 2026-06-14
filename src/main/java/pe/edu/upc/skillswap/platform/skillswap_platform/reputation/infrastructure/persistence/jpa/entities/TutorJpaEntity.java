package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

@Entity
@Table(name = "tutors")
@Getter
@Setter
@NoArgsConstructor
public class TutorJpaEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "university", length = 100, nullable = false)
    private String university;

    @Column(name = "average_score", nullable = false)
    private Double averageScore;

    @Column(name = "total_reviews", nullable = false)
    private Integer totalReviews;

    public static TutorJpaEntity fromDomain(Tutor tutor) {
        var entity = new TutorJpaEntity();
        entity.setId(tutor.getId());
        entity.setFullName(tutor.getFullName());
        entity.setUniversity(tutor.getUniversity());
        entity.setAverageScore(tutor.getAverageScore());
        entity.setTotalReviews(tutor.getTotalReviews());
        return entity;
    }

    public Tutor toDomain() {
        var tutor = new Tutor(this.fullName, this.university);
        tutor.setId(this.getId());
        tutor.setAverageScore(this.averageScore);
        tutor.setTotalReviews(this.totalReviews);
        return tutor;
    }
}