package br.com.cid.service;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;

public class GestaoUsuarios {
	
	private Usuarios usuarios;
	
	public GestaoUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	public void salvar(Usuario usuario) {
		this.usuarios.guardar(usuario);
	}
	
	public void excluir(Usuario usuario) {
		this.usuarios.remover(usuario);
	}
	
	

}
