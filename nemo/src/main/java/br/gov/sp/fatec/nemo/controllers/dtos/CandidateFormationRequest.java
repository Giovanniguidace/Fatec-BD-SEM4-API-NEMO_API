package br.gov.sp.fatec.nemo.controllers.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CandidateFormationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String institution;
    private String course;
    private LocalDate dtStart;
    private LocalDate dtEnd;
}
