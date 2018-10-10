/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.*;
import com.cptech.api.APIMinverva.Repository.ProfessorRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Aluno
 */
public class ProfessorController {
    @Autowired
    ProfessorRepository professorRepositorio;

    //Pega Todos os professors
    @RequestMapping(method = RequestMethod.GET, path = "/professor")
    public ResponseEntity<?> getAllProfessor() {
        return new ResponseEntity<>(professorRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um professor.
    @RequestMapping(method = RequestMethod.POST, path = "/professor/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Professor professor) {
        return new ResponseEntity<>(professorRepositorio.save(professor), HttpStatus.OK);
    }

    //Deleta um professor
    @RequestMapping(method = RequestMethod.PUT, path = "/professor/apagar/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable("id") Long professorID) {
        professorRepositorio.deleteById(professorID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza professor
    @RequestMapping(method = RequestMethod.PUT, path = "/professor/atualizar")
    public ResponseEntity<?> updateProfessor(@Valid @RequestBody Professor professor) {
        Professor professorAux = professorRepositorio.findById(professor.getId()).get();
        /*professorAux.setAvaliacao(professor.getAvaliacao());
        professorAux.setDiscente(professor.getDiscente());
        professorAux.setEnunciado(professor.getEnunciado());
        professor.setResolucao(professor.getResolucao());*/
        professorAux.setDiscentes(professor.getDiscentes());
        professorAux.setListas(professor.getListas());
        professorAux.setNome(professor.getNome());
        professorAux.setSenha(professor.getSenha());
        professorAux.setTutores(professor.getTutores());
        professorAux.setUsuario(professor.getUsuario());
        return new ResponseEntity<>(professorRepositorio.save(professorAux), HttpStatus.OK);
    }
    
//    //Procura Professors por professor
//    @RequestMapping(method = RequestMethod.PUT, path="/professor/procuraComProfessor")
//    public ResponseEntity<?> findProfessorByProfessor(@Valid @RequestBody Professor professor){
//        return new ResponseEntity<>(professorRepositorio.findByProfessor(professor), HttpStatus.OK);
//    }
    
        //Procura Professores por professor
//    @RequestMapping(method = RequestMethod.PUT, path="/professor/procuraComTutor")
//    public ResponseEntity<?> findProfessorByProfessor(@Valid @RequestBody Tutor tutor){
//        return new ResponseEntity<>(professorRepositorio.findByTutor(tutor), HttpStatus.OK);
//    }
}
