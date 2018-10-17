/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cptech.api.APIMinverva.Controller;

import com.cptech.api.APIMinverva.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */

@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository userRepository;
        
    @RequestMapping(method = RequestMethod.GET, path = "/usuario/procurar/{id}")
    public ResponseEntity<?> getAllUsuario(@PathVariable("id") long id){
        return new ResponseEntity<>(userRepository.findById(id),HttpStatus.OK);
    }
    //Pega Todos os Discentes
    @RequestMapping(method = RequestMethod.GET, path = "/usuario/procuraUsuario/{user}")
    public ResponseEntity<?> getDiscente(@PathVariable("user") String usuario) {
        return new ResponseEntity<>(userRepository.getByUsuario(usuario), HttpStatus.OK);
    }
    
}
