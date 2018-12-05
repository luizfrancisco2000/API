/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Models;

import java.io.Serializable;
import javax.persistence.*;

;

/**
 *
 * @author Luiz
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "usuario", nullable = false, length = 30, unique = true)
    private String usuario;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 1)
    private char tipo;
    
    @Column(name = "firstAcess", nullable = false, length = 1)
    private boolean firstAcess;
    
    @Column(name = "senha", nullable = false, length = 70)
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isFirstAcess() {
        return firstAcess;
    }

    public void setFirstAcess(boolean firstAcess) {
        this.firstAcess = firstAcess;
    }

}
