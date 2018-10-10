/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.*;
import com.cptech.api.APIMinverva.Repository.TutorRepository;
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
public class TutorController {
    @Autowired
    TutorRepository tutorRepositorio;

    //Pega Todos os tutors
    @RequestMapping(method = RequestMethod.GET, path = "/tutor")
    public ResponseEntity<?> getAllTutor() {
        return new ResponseEntity<>(tutorRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um tutor.
    @RequestMapping(method = RequestMethod.POST, path = "/tutor/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Tutor tutor) {
        return new ResponseEntity<>(tutorRepositorio.save(tutor), HttpStatus.OK);
    }

    //Deleta um tutor
    @RequestMapping(method = RequestMethod.PUT, path = "/tutor/apagar/{id}")
    public ResponseEntity<?> deleteTutor(@PathVariable("id") Long tutorID) {
        tutorRepositorio.deleteById(tutorID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza tutor
    @RequestMapping(method = RequestMethod.PUT, path = "/tutor/atualizar")
    public ResponseEntity<?> updateTutor(@Valid @RequestBody Tutor tutor) {
        Tutor tutorAux = tutorRepositorio.findById(tutor.getId()).get();
        /*tutorAux.setAvaliacao(tutor.getAvaliacao());
        tutorAux.setDiscente(tutor.getDiscente());
        tutorAux.setEnunciado(tutor.getEnunciado());
        tutor.setResolucao(tutor.getResolucao());*/
        tutorAux.setDiscentes(tutor.getDiscentes());
        tutorAux.setNome(tutor.getNome());
        tutorAux.setProfessor(tutor.getProfessor());
        tutorAux.setSenha(tutor.getSenha());
        tutorAux.setUsuario(tutor.getUsuario());
        return new ResponseEntity<>(tutorRepositorio.save(tutorAux), HttpStatus.OK);
    }
    
    //Procura Tutors por professor
    /*@RequestMapping(method = RequestMethod.PUT, path="/tutor/procuraComProfessor")
    public ResponseEntity<?> findTutorByProfessor(@Valid @RequestBody Professor professor){
        return new ResponseEntity<>(tutorRepositorio.findByProfessor(professor), HttpStatus.OK);
    }
    
        //Procura Tutors por professor
    @RequestMapping(method = RequestMethod.PUT, path="/tutor/procuraComTutor")
    public ResponseEntity<?> findTutorByProfessor(@Valid @RequestBody Tutor tutor){
        return new ResponseEntity<>(tutorRepositorio.findByTutor(tutor), HttpStatus.OK);
    }*/
    
    @RequestMapping(method = RequestMethod.PUT, path="/tutor/procuraComTutor")
    public ResponseEntity<?> findTutorByProfessor(@Valid @RequestBody Tutor tutor){
        return new ResponseEntity<>(new DiscenteController().getAllDiscenteTutor(tutor), HttpStatus.OK);
    }
}
