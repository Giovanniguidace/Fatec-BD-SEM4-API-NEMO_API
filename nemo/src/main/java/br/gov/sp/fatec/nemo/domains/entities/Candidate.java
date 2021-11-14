package br.gov.sp.fatec.nemo.domains.entities;

import br.gov.sp.fatec.nemo.domains.utils.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "can_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "can_name")
    private String name;

    @Email
    private String email;

    @CPF
    private String cpf;

    @NotBlank
    private String phone;

    @NotBlank
    private String gender;

    @NotNull
    private LocalDate birthday;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String street;

    @NotBlank
    @Column(name = "home_number")
    private Integer homeNumber;

    @NotBlank
    private String complement;

    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    @Column(name = "pretension_salary")
    private Double pretensionSalary;

    @Column(name = "desired_journey")
    private String desiredJourney;

    @Column(name = "availability")
    private String availablePeriod;

    @Column(name = "work_modality")
    private String workModality;

    @OneToMany(mappedBy = "candidate",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CandidateSkill> skills;

    @OneToMany(mappedBy = "candidate",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CandidateFormation> formations;

    @OneToMany(mappedBy = "candidate",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CandidateExp> experiences;

    @OneToMany(mappedBy = "candidate",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<DistanceMatrix> distanceMatrices;

}
