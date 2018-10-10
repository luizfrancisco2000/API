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
public interface NotaRepository extends JpaRepository<Nota, Long>{
    public List<Codigo> findByDiscente(Discente discente);
}
