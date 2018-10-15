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
@Table(name = "codigo")
public class Codigo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   
    @Column(name="assunto", nullable=false, length=255)
    private String assunto;
    
    @Column(name="enunciado", nullable=false, length=255)
    private String enunciado;
    
    @Column(name="resolucao", nullable=false, length=255, columnDefinition = "BLOB")
    private byte[] resolucao;
    
    
    @Column(name="avaliacao", nullable = false, length = 5)
    private int avaliacao;
    
    @ManyToOne
    @JoinColumn(name = "discente", nullable = true)
    private Discente discente;

    public Codigo() {
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public byte[] getResolucao() {
        return resolucao;
    }

    public void setResolucao(byte[] resolucao) {
        this.resolucao = resolucao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }
    
    
    
}
