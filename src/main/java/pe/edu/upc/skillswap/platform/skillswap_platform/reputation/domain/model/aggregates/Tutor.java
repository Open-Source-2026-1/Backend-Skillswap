package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
public class Tutor extends AbstractDomainAggregateRoot<Tutor> {

    @Setter
    private Long id;

    private String fullName;
    private String university;

    @Setter
    private Double averageScore;

    @Setter
    private Integer totalReviews;

    public Tutor(String fullName, String university) {
        this.fullName = fullName;
        this.university = university;
        this.averageScore = 0.0;
        this.totalReviews = 0;
    }

    public Tutor() {}

    public void updateInformation(String fullName, String university) {
        this.fullName = fullName;
        this.university = university;
    }
}