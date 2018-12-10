/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Exception.ResourceNotFoundException;
import com.cptech.api.APIMinverva.Models.*;
import com.cptech.api.APIMinverva.Repository.DiscenteRepository;
import com.cptech.api.APIMinverva.Repository.TutorRepository;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/api")
public class DiscenteController {

    @Autowired
    DiscenteRepository discenteRepositorio;
    
    @Autowired
    TutorRepository tutorRepositorio;

    //Pega Todos os Discentes
    @RequestMapping(method = RequestMethod.GET, path = "/discente")
    public ResponseEntity<?> getAllDiscente() {
        return new ResponseEntity<>(discenteRepositorio.findAll(), HttpStatus.OK);
    }

    //Pega Todos os Discentes
    @RequestMapping(method = RequestMethod.GET, path = "/discente/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") long id) {
        return new ResponseEntity<>(discenteRepositorio.findById(id), HttpStatus.OK);
    }

    //Cria um discente.
    @RequestMapping(method = RequestMethod.POST, path = "/discente/cadastrar")
    public ResponseEntity<?> insertDiscente(@Valid @RequestBody Discente discente) {
        return new ResponseEntity<>(discenteRepositorio.save(discente), HttpStatus.OK);
    }

    //Login de um discente
    @RequestMapping(method = RequestMethod.GET, path = "/discente/executar_login/{user}/{senha}")
    public ResponseEntity<?> login(@PathVariable("user") String user, @PathVariable("senha") String senha) {
        Discente discente;
        discente = Discente.getInstance();
        discente = discenteRepositorio.getByUsuario(user);
        if (discente != null) {
            if (discente.getSenha().equals(senha)) {
                if (discente.getTipo() == 'A') {
                    return new ResponseEntity<>(discente, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //Deleta um discente
    @RequestMapping(method = RequestMethod.PUT, path = "/discente/apagar/{id}")
    public ResponseEntity<?> deleteDiscente(@PathVariable("id") Long discenteID) {
        discenteRepositorio.deleteById(discenteID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza discente
    @RequestMapping(method = RequestMethod.PUT, path = "/discente/atualizar")
    public ResponseEntity<?> updateAluno(@Valid @RequestBody Discente discente) {
        Discente discenteAux = discenteRepositorio.findById(discente.getId()).get();
        discenteAux.setAtivo(discente.isAtivo());
        discenteAux.setCodigos(discente.getCodigos());
        discenteAux.setNome(discente.getNome());
        discenteAux.setNotas(discente.getNotas());
        discenteAux.setProfessor(discente.getProfessor());
        discenteAux.setSenha(discente.getSenha());
        discenteAux.setTurma(discente.getTurma());
        discenteAux.setTutor(discente.getTutor());
        discenteAux.setUsuario(discente.getUsuario());
        return new ResponseEntity<>(discenteRepositorio.save(discenteAux), HttpStatus.OK);
    }

    //Procura Todos os Discentes de uma turma
        @RequestMapping(method = RequestMethod.GET, path = "/discente/ProcuraTurma/{turma}")
    public ResponseEntity<?> getAllDiscenteTurma(@PathVariable("turma") String turma) {
        return new ResponseEntity<>(discenteRepositorio.getByTurma(turma), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT, path = "/discente/ProcuraTutor/{id}")
    public ResponseEntity<?> getAllDiscenteTutor(@PathVariable("id") long id) {
        return new ResponseEntity<>(discenteRepositorio.getByTutor(tutorRepositorio.findById(id).get()), HttpStatus.OK);
    }
}
