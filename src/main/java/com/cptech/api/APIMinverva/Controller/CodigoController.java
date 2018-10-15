/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.Codigo;
import com.cptech.api.APIMinverva.Models.Discente;
import com.cptech.api.APIMinverva.Repository.CodigoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aluno
 */
public class CodigoController {
    @Autowired
    CodigoRepository codigoRepositorio;

    //Pega Todos os codigos
    @RequestMapping(method = RequestMethod.GET, path = "/codigo")
    public ResponseEntity<?> getAllCodigo() {
        return new ResponseEntity<>(codigoRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um codigo.
    @RequestMapping(method = RequestMethod.POST, path = "/codigo/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Codigo codigo) {
        return new ResponseEntity<>(codigoRepositorio.save(codigo), HttpStatus.OK);
    }

    //Deleta um codigo
    @RequestMapping(method = RequestMethod.PUT, path = "/codigo/apagar/{id}")
    public ResponseEntity<?> deleteCodigo(@PathVariable("id") Long codigoID) {
        codigoRepositorio.deleteById(codigoID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza codigo
    @RequestMapping(method = RequestMethod.PUT, path = "/codigo/atualizar")
    public ResponseEntity<?> updateCodigo(@Valid @RequestBody Codigo codigo) {
        Codigo codigoAux = codigoRepositorio.findById(codigo.getId()).get();
        codigoAux.setAvaliacao(codigo.getAvaliacao());
        codigoAux.setDiscente(codigo.getDiscente());
        codigoAux.setEnunciado(codigo.getEnunciado());
        codigoAux.setResolucao(codigo.getResolucao());
        return new ResponseEntity<>(codigoRepositorio.save(codigoAux), HttpStatus.OK);
    }
    
    //Procura Codigos
    @RequestMapping(method = RequestMethod.GET, path="/codigo/ProcuraAluno")
    public ResponseEntity<?> findCodigoByAluno(@Valid @RequestBody Discente discente){
        return new ResponseEntity<>(codigoRepositorio.findByDiscente(discente), HttpStatus.OK);
    }
}
