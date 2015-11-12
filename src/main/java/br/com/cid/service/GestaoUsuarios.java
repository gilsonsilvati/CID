package br.com.cid.service;

import java.io.Serializable;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;

public class GestaoUsuarios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuarios usuarios;
	
	public GestaoUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	public void salvar(Usuario usuario) {
		this.usuarios.guardar(usuario);
	}
	
	/*
	public void salvar(Usuario usuario) throws RegraNegocioException {
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
	
	public void excluir(Usuario usuario) {
		this.usuarios.remover(usuario);
	}
	
	public void buscarPorNome(String nome) {
		this.usuarios.porNome(nome);
	}

}
