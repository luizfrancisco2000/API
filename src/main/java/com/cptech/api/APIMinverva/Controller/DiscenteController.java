/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Exception.ResourceNotFoundException;
import com.cptech.api.APIMinverva.Models.Discente;
import com.cptech.api.APIMinverva.Repository.DiscenteRepository;
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
    DiscenteRepository discenterepositorio;
    
    @RequestMapping(method = RequestMethod.GET, path = "/discente")
    public ResponseEntity<?> getAllDiscente(){
        return new ResponseEntity<>(discenterepositorio.findAll(),HttpStatus.OK);
    }
    
    //Cria um discente.
    @RequestMapping(method = RequestMethod.POST, path = "/discente/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Discente discente) {
        return new ResponseEntity<>(discenterepositorio.save(discente), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/discente/executar_login/{user}/{senha}")
    public ResponseEntity<?> login(@PathVariable("user") String user, @PathVariable("senha") String senha) {
        Discente discente;
        discente = Discente.getInstance();
        discente = discenterepositorio.getByUsuario(user);
        if (discente != null) {
            if (discente.getSenha().equals(senha)) {
                if(discente.getTipo()=='A'){
                    return new ResponseEntity<>(discente, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    /*
    @GetMapping("/discente/{id}")
    public Discente getDiscenteById(@PathVariable(value = "id")Long discenteId){
        return (Discente) discenterepostorio.findById(discenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Dicente","id", discenteId));
    }
    
    @GetMapping("/discente/{login}/{senha}")
    public Discente loginDiscente(@PathVariable(value="login") String userDiscente, @PathVariable(value="senha") String senhaDiscente){
        List<Discente> discentes = discenterepostorio.findAll();
        for (Discente aux : discentes) {
            if(aux.getUsuario().equals(userDiscente) && aux.getSenha().equals(senhaDiscente) && aux.getTipo()=='A'){
                return aux;
            }
        }
        return null;
    }
    
    @PostMapping("/alternativas")
    public Discente createDiscente(@Valid @RequestBody Discente discente){
        return discenterepostorio.save(discente);
    }
    
    @PutMapping("/discente/{id}")
    public Discente updateDiscentes(@PathVariable(value = "id") Long discenteID, 
            @Valid @RequestBody Discente discenteDetails){
        Discente discente = discenterepostorio.findById(discenteID)
                            .orElseThrow(() -> new ResourceNotFoundException("Dicente", "discente", discenteID));
        discente.setCodigos(discenteDetails.getCodigos());
        discente.setNome(discenteDetails.getNome());
        discente.setNotas(discenteDetails.getNotas());
        discente.setTutor(discenteDetails.getTutor());
        discente.setProfessor(discenteDetails.getProfessor());
        discente.setTurma(discenteDetails.getTurma());
        discente.setUsuario(discenteDetails.getUsuario());
        Discente discenteUpdate = discenterepostorio.save(discente);
        return discenteUpdate;
    }
    
    @DeleteMapping("/discente/{id}")
    public ResponseEntity<?> deleteDiscente(@PathVariable(value="id") Long discenteId){
        Discente discente = discenterepostorio.findById(discenteId)
                            .orElseThrow(() -> new ResourceNotFoundException("Dicente", "discente", discenteId));
        discenterepostorio.deleteById(discenteId);
        
        return ResponseEntity.ok().build();
        
    }*/
} 
