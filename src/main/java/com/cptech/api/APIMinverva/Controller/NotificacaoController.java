/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.Notificacao;
import com.cptech.api.APIMinverva.Models.Usuario;
import com.cptech.api.APIMinverva.Repository.NotificacaoRepository;
import com.cptech.api.APIMinverva.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/api")
public class NotificacaoController {
    @Autowired
    NotificacaoRepository notificaaoRepositorio;
    @Autowired
    UsuarioRepository discenteRepositorio;
    //Pega Todos os notificaaos
    @RequestMapping(method = RequestMethod.GET, path = "/notificacao")
    public ResponseEntity<?> getAllNotificacao() {
        return new ResponseEntity<>(notificaaoRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um notificaao.
    @RequestMapping(method = RequestMethod.POST, path = "/notificacao/cadastrar/{id}")
    public ResponseEntity<?> insertNotificacao(@Valid @RequestBody Notificacao notificaao, @PathVariable("id") Long userID) {
        ObjectMapper mapp = new ObjectMapper();
        notificaao.setUsuario(mapp.convertValue(discenteRepositorio.findById(userID).get(), Usuario.class));
        return new ResponseEntity<>(notificaaoRepositorio.save(notificaao), HttpStatus.OK);
    }

    //Deleta um notificaao
    @RequestMapping(method = RequestMethod.PUT, path = "/notificacao/apagar/{id}")
    public ResponseEntity<?> deleteNotificacao(@PathVariable("id") Long notificaaoID) {
        notificaaoRepositorio.deleteById(notificaaoID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza notificaao
    @RequestMapping(method = RequestMethod.PUT, path = "/notificacao/atualizar/{id}")
    public ResponseEntity<?> updateNotificacao(@Valid @RequestBody Notificacao notificaao,@PathVariable("id") Long notificaaoID) {
        Notificacao notificaaoAux = notificaaoRepositorio.findById(notificaaoID).get();
        return new ResponseEntity<>(notificaaoRepositorio.save(notificaaoAux), HttpStatus.OK);
    }
    
    
    //procura notificacacao via ID
    @RequestMapping(method = RequestMethod.GET, path = "/notificacao/user/{id}")
    public ResponseEntity<?> findByUser(@PathVariable("id") Long userID){
        return new ResponseEntity<>(notificaaoRepositorio.findByUser(discenteRepositorio.findById(userID).get()), HttpStatus.OK);
    }
}
