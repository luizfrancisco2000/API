/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Repository;

import com.cptech.api.APIMinverva.Models.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
public interface ListaRepository extends JpaRepository<Lista, Long>{
    public List<Lista> findByProfessor(Professor professor);
    public List<Lista> findByTutor(Tutor tutor);
}
