/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.*;
import com.cptech.api.APIMinverva.Repository.DiscenteRepository;
import com.cptech.api.APIMinverva.Repository.TutorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */

@RestController
@RequestMapping("/api")
public class TutorController {
    @Autowired
    TutorRepository tutorRepositorio;
    @Autowired
    DiscenteRepository discenteRepositorio;
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
        //Login de um Professor
    @RequestMapping(method = RequestMethod.GET, path = "/tutor/executar_login/{user}/{senha}")
    public ResponseEntity<?> login(@PathVariable("user") String user, @PathVariable("senha") String senha) {
        Tutor tutor;
        tutor = Tutor.getInstance();
        tutor = tutorRepositorio.getByUsuario(user);
        if (tutor != null) {
            if (tutor.getSenha().equals(senha)) {
                if (tutor.getTipo() == 'T') {
                    return new ResponseEntity<>(tutor, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
    
    
    
    @RequestMapping(method = RequestMethod.GET, path="/tutor/ProcurarUsuario/{usuario}")
    public ResponseEntity<?> findTutorByUser(@PathVariable("usuario") String usuario){
        return new ResponseEntity<>(tutorRepositorio.tutorGetByUser(usuario), HttpStatus.OK);
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
    
    
    @RequestMapping(method = RequestMethod.PUT, path="/tutor/adcTutorando/{idTutor}/{idTutorando}")
    public ResponseEntity<?> insertTutorando(@PathVariable("idTutor")Long idTutor, @PathVariable("idTutorando")Long idTutorando){
        return new ResponseEntity<>(tutorRepositorio.tutorSalveTutorando(discenteRepositorio.findById(idTutorando).get(),idTutor),HttpStatus.OK);
    }
}
