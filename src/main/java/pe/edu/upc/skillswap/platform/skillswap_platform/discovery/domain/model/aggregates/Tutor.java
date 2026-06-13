package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.util.List;

@Getter
@Entity
@Table(name = "tutors")
public class Tutor extends AbstractDomainAggregateRoot<Tutor> {

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    private String university;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String bio;

    @Setter
    private Double rating;

    @Setter
    @ElementCollection
    @CollectionTable(name = "tutor_skills", joinColumns = @JoinColumn(name = "tutor_id"))
    @Column(name = "skill")
    private List<String> skills;

    @Setter
    private Boolean available;

    @Setter
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Setter
    private String specialty;

    @Setter
    @Column(name = "portfolio_url")
    private String portfolioUrl;

    @Setter
    @Column(name = "years_experience")
    private Integer yearsExperience;


    public Tutor() {}

    public Tutor(String name, String university, String bio, Double rating,
                 List<String> skills, Boolean available, String avatarUrl,
                 String specialty, String portfolioUrl, Integer yearsExperience) {
        this.name = name;
        this.university = university;
        this.bio = bio;
        this.rating = rating;
        this.skills = skills;
        this.available = available;
        this.avatarUrl = avatarUrl;
        this.specialty = specialty;
        this.portfolioUrl = portfolioUrl;
        this.yearsExperience = yearsExperience;
    }

}