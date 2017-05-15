/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.api.controller;

import com.test.model.api.model.Usuario;
import com.test.model.api.services.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lprates
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {
    
    
    @Autowired
    UsuarioService usuarioService; 

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }    
    
    /**
     * Retorna todos os usuarios
     * @return 
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
            List<Usuario> usuarios = usuarioService.findAllUsuarios() ;
            if (usuarios.isEmpty()) {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    /**
     * Retorna usuario por id 
     * @param id
     * @return 
     */
    
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") long id) {
            
        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    
    
    /**
     * Criar usuario 
     * @param usuario
     * @return 
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
            
            if (usuarioService.isUsuarioExist(usuario)) {
                    return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            }
            usuarioService.saveUsuario(usuario);

            return new ResponseEntity<String>(HttpStatus.CREATED);
    }
    
    
    /**
     * Atualizar  usuario 
     * @param usuario
     * @return 
     */
    
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
            
            Usuario usuarioFind = usuarioService.findById(id);

            if (usuarioFind == null ){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            
            usuarioFind.setId(id);
            usuarioFind.setNome(usuario.getNome());
            usuarioFind.setEmail(usuario.getEmail());
            usuarioFind.setSexo(usuario.getSexo());
            usuarioFind.setTelefone(usuario.getTelefone());

            usuarioService.updateUsuario(usuario);

            return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    
    
    /**
     * Deleta usuario por id 
     * @param id
     * @return 
     */
    
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") long id) {

        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        usuarioService.deleteUsuarioById(id);
        
        return new ResponseEntity<Usuario>(HttpStatus.OK);
    }
    
    
    
}
