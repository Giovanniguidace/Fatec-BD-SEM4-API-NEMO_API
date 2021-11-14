package br.gov.sp.fatec.nemo.domains.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "institution")
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inst_id")
    private Long id;

    @Column(name = "inst_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}