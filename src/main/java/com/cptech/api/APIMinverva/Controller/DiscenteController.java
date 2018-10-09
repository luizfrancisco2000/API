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

    //Pega Todos os Discentes
    @RequestMapping(method = RequestMethod.GET, path = "/discente")
    public ResponseEntity<?> getAllDiscente() {
        return new ResponseEntity<>(discenterepositorio.findAll(), HttpStatus.OK);
    }

    //Cria um discente.
    @RequestMapping(method = RequestMethod.POST, path = "/discente/cadastrar")
    public ResponseEntity<?> insertProfessor(@Valid @RequestBody Discente discente) {
        return new ResponseEntity<>(discenterepositorio.save(discente), HttpStatus.OK);
    }

    //Login de um discente
    @RequestMapping(method = RequestMethod.GET, path = "/discente/executar_login/{user}/{senha}")
    public ResponseEntity<?> login(@PathVariable("user") String user, @PathVariable("senha") String senha) {
        Discente discente;
        discente = Discente.getInstance();
        discente = discenterepositorio.getByUsuario(user);
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
    @RequestMapping(method = RequestMethod.PUT, path="/discente/apagar/{id}")
    public ResponseEntity<?> deleteDiscente(@PathVariable("id") Long discenteID){
        discenterepositorio.deleteById(discenteID);
        return new ResponseEntity<>("Apagado com Sucesso!", HttpStatus.OK);
    }
    
    //Faz Atualização de um Discente
//    @RequestMapping(method = RequestMethod.PUT, path = "/aluno/{id}")
//    public ResponseEntity<?> updateAluno(@PathVariable("id") Long aluno_id,
//            @Valid @RequestBody Aluno aluno_details) {
//
//        Aluno aluno = alunoRepository.findById(aluno_id)
//                .orElseThrow(() -> new ResourceNotFoundException("Aluno", "aluno", aluno_id));
//
//        aluno.setNome(aluno_details.getNome());
//        aluno.setObs(aluno_details.getObs());
//        aluno.setEmail(aluno_details.getEmail());
//
//        //TALVEZ UM SET PARA AS NOTAS AQUI
//        //VOU VER DEPOIS, TÁ?
//        //NÃO ESQUECE
//        return new ResponseEntity<>(alunoRepository.save(aluno), HttpStatus.OK);
//}
    
}