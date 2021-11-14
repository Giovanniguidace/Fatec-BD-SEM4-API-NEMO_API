package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_formation")
public class CandidateFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("candidateId") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "fk_can_id")
    @JsonIgnore
    private Candidate candidate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_inst_id", referencedColumnName = "inst_id")
    private Institution institution;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_course_id", referencedColumnName = "course_id")
    private Course course;

    @NotNull
    @Column(name = "dt_start")
    private LocalDate dtStart;

    @NotNull
    @Column(name = "dt_end")
    private LocalDate dtEnd;

    @Override
    public String toString() {
        return "formations{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", institution=" + institution +
                ", course=" + course +
                '}';
    }


}