/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Repository;


import com.cptech.api.APIMinverva.Models.Notificacao;
import com.cptech.api.APIMinverva.Models.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Aluno
 */
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>{
    @Query(value="SELECT * FROM notificacao n WHERE n.usuario = ?1", nativeQuery = true)
    public List<Notificacao> findByUser(Usuario user);
}
