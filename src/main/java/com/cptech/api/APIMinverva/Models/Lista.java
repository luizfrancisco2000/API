/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Models;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author Aluno
 */
@Entity
@Table(name="lista")
public class Lista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="assunto", nullable=false, length=40)
    private String assunto;
    
    @Column(name="lista", nullable=false, length=255, columnDefinition ="BLOB")
    private byte[] lista;
    
    @ManyToOne
    @JoinColumn(name = "professor", nullable = true)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "tutor", nullable = true)
    private Tutor tutor;
    public Lista(){
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public byte[] getLista() {
        return lista;
    }

    public void setLista(byte[] lista) {
        this.lista = lista;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    
}
