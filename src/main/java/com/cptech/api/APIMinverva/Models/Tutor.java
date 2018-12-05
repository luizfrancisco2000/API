/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "tutor")
public class Tutor extends Usuario implements Serializable{
    
    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "tutor")
    private List<Discente> discentes;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professor", nullable = true)
    private Professor professor;

    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "tutor")
    private List<Lista> listas;

    private static Tutor instance;

    public Tutor() {
    }

    public static Tutor getInstance() {
        if (instance == null) {
            instance = new Tutor();
        }
        return instance;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    public List<Discente> getDiscentes() {
        return discentes;
    }

    public void setDiscentes(List<Discente> discentes) {
        this.discentes = discentes;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
