package com.xmenms.models;


import javax.persistence.*;

@Entity
@Table(name = "mutants")
public class Mutant {

    public Mutant(){}

    public Mutant(String andSequence, boolean isMutant) {
        this.andSequence = andSequence;
        this.isMutant = isMutant;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "and_sequence")
    private String andSequence;

    @Column(name = "is_mutant")
    private boolean isMutant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAndSequence() {
        return andSequence;
    }

    public void setAndSequence(String andSequence) {
        this.andSequence = andSequence;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
