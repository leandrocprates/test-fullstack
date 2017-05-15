/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.model.api.services;

import com.test.model.api.model.Usuario;
import com.test.model.api.repositories.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lprates
 */
@Service("usuarioService")
//@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    
	@Autowired
	private UsuarioRepository userRepository;
    
        
	public Usuario findById(Long id) {
		return userRepository.findOne(id);
	}

	public Usuario findByNome(String nome) {
		return userRepository.findByNome(nome);
	}

	public void saveUsuario(Usuario user) {
		userRepository.save(user);
	}

	public void updateUsuario(Usuario user){
		saveUsuario(user);
	}

	public void deleteUsuarioById(Long id){
		userRepository.delete(id);
	}

	public void deleteAllUsuarios(){
		userRepository.deleteAll();
	}

	public List<Usuario> findAllUsuarios(){
		return userRepository.findAll();
	}

	public boolean isUsuarioExist(Usuario user) {
		return findByNome(user.getNome()) != null;
	}
   
}
