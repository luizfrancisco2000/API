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
@Table(name = "professor")
public class Professor extends Usuario implements Serializable{
    
    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "professor")
    private List<Tutor> tutores;

    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "professor")
    private List<Discente> discentes;
    
    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "professor")
    private List<Lista> listas;

    private static Professor instance;
    
    
    public Professor() {

    }

    public static Professor getInstance() {
        if (instance == null) {
            instance = new Professor();
        }
        return instance;
    }

    public List<Tutor> getTutores() {
        return tutores;
    }

    public void setTutores(List<Tutor> tutores) {
        this.tutores = tutores;
    }

    public List<Discente> getDiscentes() {
        return discentes;
    }

    public void setDiscentes(List<Discente> discentes) {
        this.discentes = discentes;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

}
