/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Models.Codigo;
import com.cptech.api.APIMinverva.Models.Discente;
import com.cptech.api.APIMinverva.Repository.CodigoRepository;
import com.cptech.api.APIMinverva.Repository.DiscenteRepository;
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
public class CodigoController {
    @Autowired
    CodigoRepository codigoRepositorio;
    @Autowired
    DiscenteRepository discenteRepositorio;
    //Pega Todos os codigos
    @RequestMapping(method = RequestMethod.GET, path = "/codigo")
    public ResponseEntity<?> getAllCodigo() {
        return new ResponseEntity<>(codigoRepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um codigo.
    @RequestMapping(method = RequestMethod.POST, path = "/codigo/cadastrar/{id}")
    public ResponseEntity<?> insertCodigo(@Valid @RequestBody Codigo codigo, @PathVariable("id") Long discenteID) {
        ObjectMapper mapp = new ObjectMapper();
        codigo.setDiscente(mapp.convertValue(discenteRepositorio.findById(discenteID).get(), Discente.class));
        return new ResponseEntity<>(codigoRepositorio.save(codigo), HttpStatus.OK);
    }

    //Deleta um codigo
    @RequestMapping(method = RequestMethod.PUT, path = "/codigo/apagar/{id}")
    public ResponseEntity<?> deleteCodigo(@PathVariable("id") Long codigoID) {
        codigoRepositorio.deleteById(codigoID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    
    //atualiza codigo
    @RequestMapping(method = RequestMethod.PUT, path = "/codigo/atualizar/{id}")
    public ResponseEntity<?> updateCodigo(@Valid @RequestBody Codigo codigo,@PathVariable("id") Long codigoID) {
        Codigo codigoAux = codigoRepositorio.findById(codigoID).get();
        codigoAux.setAvaliacao(codigo.getAvaliacao());
        codigoAux.setDiscente(codigo.getDiscente());
        codigoAux.setEnunciado(codigo.getEnunciado());
        codigoAux.setResolucao(codigo.getResolucao());
        return new ResponseEntity<>(codigoRepositorio.save(codigoAux), HttpStatus.OK);
    }
    
    //Procura Codigos
    @RequestMapping(method = RequestMethod.GET, path="/codigo/ProcuraAluno/{id}")
    public ResponseEntity<?> findCodigoByAluno(@PathVariable("id") Long discenteID){
        ObjectMapper mapp = new ObjectMapper();
        return new ResponseEntity<>(codigoRepositorio.findByDiscente(mapp.convertValue(discenteRepositorio.findById(discenteID).get(), Discente.class)), HttpStatus.OK);
    }
}
