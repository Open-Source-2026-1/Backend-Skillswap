package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.CreateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.UpdateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.List;

@Entity
@Table(name = "tutors")
public class Tutor extends AuditableAbstractAggregateRoot<Tutor> {

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(name = "university")
    private String university;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String bio;

    @Getter
    @Min(0)
    @Max(5)
    @Column(name = "rating")
    private Double rating;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "tutor_skills", joinColumns = @JoinColumn(name = "tutor_id"))
    @Column(name = "skill")
    private List<String> skills;

    @Getter
    @Column(name = "available")
    private Boolean available;

    @Getter
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Getter
    @Column(name = "specialty")
    private String specialty;

    @Getter
    @Column(name = "portfolio_url")
    private String portfolioUrl;

    @Getter
    @Column(name = "years_experience")
    private Integer yearsExperience;

    public Tutor() {}

    public Tutor(CreateTutorCommand command) {
        this.name = command.name();
        this.university = command.university();
        this.bio = command.bio();
        this.rating = command.rating();
        this.skills = command.skills();
        this.available = command.available();
        this.avatarUrl = command.avatarUrl();
        this.specialty = command.specialty();
        this.portfolioUrl = command.portfolioUrl();
        this.yearsExperience = command.yearsExperience();
    }

    public Tutor updateInformation(UpdateTutorCommand command) {
        this.name = command.name();
        this.university = command.university();
        this.bio = command.bio();
        this.rating = command.rating();
        this.skills = command.skills();
        this.available = command.available();
        this.avatarUrl = command.avatarUrl();
        this.specialty = command.specialty();
        this.portfolioUrl = command.portfolioUrl();
        this.yearsExperience = command.yearsExperience();
        return this;
    }

    public boolean isAvailable() {
        return Boolean.TRUE.equals(this.available);
    }
}