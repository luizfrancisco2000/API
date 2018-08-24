/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Exception.ResourceNotFoundException;
import com.cptech.api.APIMinverva.Models.Discente;
import com.cptech.api.APIMinverva.Repository.DiscenteRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    DiscenteRepository discenterepostorio;
    
    @GetMapping("/discente")
    public List<Discente> getAllDiscente(){
        Discente discente = new Discente();
        discente.setCodigos(null);
        discente.setNome("Chico");
        discente.setNotas(null);
        discente.setProfessor(null);
        discente.setSenha("chiquinho123");
        discente.setTipo('A');
        discente.setTurma("IINF11-B");
        discente.setTutor(null);
        discente.setUsuario("chiquinho123");
        discenterepostorio.save(discente);
        return discenterepostorio.findAll();
    }
    
    @GetMapping("/discente/{id}")
    public Discente getDiscenteById(@PathVariable(value = "id")Long discenteId){
        return (Discente) discenterepostorio.findById(discenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Dicente","id", discenteId));
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
        
    }
    
} 
