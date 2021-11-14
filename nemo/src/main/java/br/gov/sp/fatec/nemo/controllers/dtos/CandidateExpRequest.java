package br.gov.sp.fatec.nemo.controllers.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CandidateExpRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String company;
    private String post;
    private LocalDate dtStart;
    private LocalDate dtEnd;
    private String description;
}
