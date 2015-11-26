package br.com.cid.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;
import br.com.cid.util.jpa.Transactional;

public class GestaoUsuarios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public void salvar(Usuario usuario) {
		this.usuarios.guardar(usuario);
	}
	
	/*
	public void salvar(Usuario usuario) throws NegocioException {
		if (isExist(usuario)) {
			throw new RegraNegocioException("Já existe um usuário igual a este.");
		}
		
		this.usuarios.guardar(usuario);
	}
	
	private boolean isExist(Usuario usuario) {
		Usuario usuarioSemelhante = this.usuarios.comDadosIguais(usuario);
		
		return usuarioSemelhante != null && usuarioSemelhante.equals(usuario);
	}
	*/
	
	@Transactional
	public void excluir(Usuario usuario) {
		this.usuarios.remover(usuario);
	}

}
