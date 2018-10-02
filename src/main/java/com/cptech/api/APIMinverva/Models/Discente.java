/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "discente")
public class Discente extends Usuario implements Serializable {

    @Column(name = "turma", nullable = false, length = 8)
    private String turma;
    @ManyToOne
    @JoinColumn(name = "professor", nullable = true)
    private Professor professor;
    @ManyToOne
    @JoinColumn(name = "tutor", nullable = true)
    private Tutor tutor;
    @OneToMany(cascade = ALL)
    private List<Nota> notas;
    @OneToMany(cascade = ALL, mappedBy = "discente")
    private List<Codigo> codigos;
    /**
     * Quando o professor bloquear as questões em momentos de provas
     */
    @Column(name = "ativo", nullable = false, length = 1)
    private boolean ativo;
    
    private static Discente instance;
    
    public static Discente getInstance(){
        if(instance == null){
            instance = new Discente();
        }
        return instance;
    }
    
    public Discente() {
    }
    
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Codigo> getCodigos() {
        return codigos;
    }

    public void setCodigos(List<Codigo> codigos) {
        this.codigos = codigos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
