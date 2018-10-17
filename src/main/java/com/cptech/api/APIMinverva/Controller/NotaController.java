/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.*;
import com.cptech.api.APIMinverva.Repository.NotaRepository;
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
public class NotaController {
    @Autowired
    NotaRepository notaRepositorio;

    //Pega Todos os notas
    @RequestMapping(method = RequestMethod.GET, path = "/nota")
    public ResponseEntity<?> getAllNota() {
        return new ResponseEntity<>(notaRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um nota.
    @RequestMapping(method = RequestMethod.POST, path = "/nota/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Nota nota) {
        return new ResponseEntity<>(notaRepositorio.save(nota), HttpStatus.OK);
    }

    //Deleta um nota
    @RequestMapping(method = RequestMethod.PUT, path = "/nota/apagar/{id}")
    public ResponseEntity<?> deleteNota(@PathVariable("id") Long notaID) {
        notaRepositorio.deleteById(notaID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza nota
    @RequestMapping(method = RequestMethod.PUT, path = "/nota/atualizar")
    public ResponseEntity<?> updateNota(@Valid @RequestBody Nota nota) {
        Nota notaAux = notaRepositorio.findById(nota.getId()).get();
        /*notaAux.setAvaliacao(nota.getAvaliacao());
        notaAux.setDiscente(nota.getDiscente());
        notaAux.setEnunciado(nota.getEnunciado());
        nota.setResolucao(nota.getResolucao());*/
        notaAux.setNota(nota.getNota());
        notaAux.setBimestre(nota.getBimestre());
        notaAux.setDiscente(nota.getDiscente());
        notaAux.setNota(nota.getNota());
        notaAux.setProva(nota.getProva());
        return new ResponseEntity<>(notaRepositorio.save(notaAux), HttpStatus.OK);
    }
    
    
        //Procura Notas por Discente
    @RequestMapping(method = RequestMethod.PUT, path="/nota/procuraDiscente")
    public ResponseEntity<?> findNotaByProfessor(@Valid @RequestBody Discente discente){
        return new ResponseEntity<>(notaRepositorio.findByDiscente(discente), HttpStatus.OK);
    }
}
