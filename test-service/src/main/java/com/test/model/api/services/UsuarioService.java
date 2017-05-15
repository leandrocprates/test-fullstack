/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.model.api.services;

import com.test.model.api.model.Usuario;
import java.util.List;

/**
 *
 * @author lprates
 */
public interface UsuarioService {
    
	Usuario findById(Long id);

	Usuario findByNome(String name);

	void saveUsuario(Usuario user);

	void updateUsuario(Usuario user);

	void deleteUsuarioById(Long id);

	void deleteAllUsuarios();

	List<Usuario> findAllUsuarios();

	boolean isUsuarioExist(Usuario user);
    
}
