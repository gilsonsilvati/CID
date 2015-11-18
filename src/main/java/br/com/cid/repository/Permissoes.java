package br.com.cid.repository;

import java.util.List;

import br.com.cid.model.PermissaoUsuario;

public interface Permissoes {
	
	public List<PermissaoUsuario> todos();
	public PermissaoUsuario porId(Long id);
	public List<String> porEmail(String email);
	public PermissaoUsuario comDadosIguais(PermissaoUsuario permissaoUsuario);
	
	public PermissaoUsuario guardar(PermissaoUsuario permissaoUsuario);
	
	public void remover(PermissaoUsuario permissaoUsuario);

}
