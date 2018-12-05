/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "notas")
public class Nota implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="bimestre", nullable = false)
    private int bimestre;
    
    @Column(name="prova", nullable = false)
    private int prova;
    
    @Column(name = "nota", nullable=false)
    private float nota;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "discente", nullable = true)
    private Discente discente;

    public Nota() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public int getProva() {
        return prova;
    }

    public void setProva(int prova) {
        this.prova = prova;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }
    
    
}
