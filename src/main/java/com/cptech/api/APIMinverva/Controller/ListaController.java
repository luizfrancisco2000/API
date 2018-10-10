/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.*;
import com.cptech.api.APIMinverva.Repository.ListaRepository;
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
public class ListaController {
        @Autowired
    ListaRepository listaRepositorio;

    //Pega Todos os listas
    @RequestMapping(method = RequestMethod.GET, path = "/lista")
    public ResponseEntity<?> getAllLista() {
        return new ResponseEntity<>(listaRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um lista.
    @RequestMapping(method = RequestMethod.POST, path = "/lista/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Lista lista) {
        return new ResponseEntity<>(listaRepositorio.save(lista), HttpStatus.OK);
    }

    //Deleta um lista
    @RequestMapping(method = RequestMethod.PUT, path = "/lista/apagar/{id}")
    public ResponseEntity<?> deleteLista(@PathVariable("id") Long listaID) {
        listaRepositorio.deleteById(listaID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza lista
    @RequestMapping(method = RequestMethod.PUT, path = "/lista/atualizar")
    public ResponseEntity<?> updateLista(@Valid @RequestBody Lista lista) {
        Lista listaAux = listaRepositorio.findById(lista.getId()).get();
        /*listaAux.setAvaliacao(lista.getAvaliacao());
        listaAux.setDiscente(lista.getDiscente());
        listaAux.setEnunciado(lista.getEnunciado());
        lista.setResolucao(lista.getResolucao());*/
        listaAux.setAssunto(lista.getAssunto());
        listaAux.setLista(lista.getLista());
        listaAux.setProfessor(lista.getProfessor());
        return new ResponseEntity<>(listaRepositorio.save(listaAux), HttpStatus.OK);
    }
    
    //Procura Listas por professor
    @RequestMapping(method = RequestMethod.PUT, path="/lista/procuraComProfessor")
    public ResponseEntity<?> findListaByProfessor(@Valid @RequestBody Professor professor){
        return new ResponseEntity<>(listaRepositorio.findByProfessor(professor), HttpStatus.OK);
    }
    
        //Procura Listas por professor
    @RequestMapping(method = RequestMethod.PUT, path="/lista/procuraComTutor")
    public ResponseEntity<?> findListaByProfessor(@Valid @RequestBody Tutor tutor){
        return new ResponseEntity<>(listaRepositorio.findByTutor(tutor), HttpStatus.OK);
    }
}
