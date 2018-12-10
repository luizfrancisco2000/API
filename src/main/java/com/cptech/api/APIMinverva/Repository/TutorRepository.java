/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Repository;

import com.cptech.api.APIMinverva.Models.Discente;
import com.cptech.api.APIMinverva.Models.Tutor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Aluno
 */
public interface TutorRepository extends JpaRepository<Tutor, Long>{
    public Tutor getByUsuario(String usuario);
    
    @Query(value = "SELECT * FROM tutor t where t.nome like %?1% or t.usuario like %?1% and t.tipo = 'T'", nativeQuery = true)
    public List<Tutor> tutorGetByUser(String u);
    
    @Query(value = "UPDATE tutor t SET t.discente = ?1 WHERE t.id=?2", nativeQuery = true)
    public boolean tutorSalveTutorando(Discente discente, long id);
}
