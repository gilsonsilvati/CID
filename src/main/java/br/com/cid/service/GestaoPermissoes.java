package br.com.cid.service;

import java.io.Serializable;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;

public class GestaoPermissoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Permissoes permissoes;
	
	public GestaoPermissoes(Permissoes permissoes) {
		this.permissoes = permissoes;
	}
	
	public void salvar(PermissaoUsuario permissaoUsuario) {
		this.permissoes.guardar(permissaoUsuario);
	}
	
	/*
	public void salvar(PermissaoUsuario permissaoUsuario) throws RegraNegocioException {
		if (isExist(permissaoUsuario)) {
			throw new RegraNegocioException("Já existe um usuário igual a este.");
		}
		
		this.permissoes.guardar(permissaoUsuario);
	}
	
	private boolean isExist(PermissaoUsuario permissaoUsuario) {
		PermissaoUsuario usuarioSemelhante = this.permissoes.comDadosIguais(permissaoUsuario);
		
		return usuarioSemelhante != null && usuarioSemelhante.equals(permissaoUsuario);
	}
	*/
	
	public void excluir(PermissaoUsuario permissaoUsuario) {
		this.permissoes.remover(permissaoUsuario);
	}
	
	public void buscarPorEmail(String email) {
		this.permissoes.porEmail(email);
	}

}
