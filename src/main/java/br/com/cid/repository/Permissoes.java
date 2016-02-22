package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import br.com.cid.model.PermissaoUsuario;

public interface Permissoes extends Serializable {
	
	public List<PermissaoUsuario> todos();
	public PermissaoUsuario porId(Long id);
	public List<PermissaoUsuario> porEmail(String email);
	public PermissaoUsuario comDadosIguais(PermissaoUsuario permissaoUsuario);
	
	public PermissaoUsuario guardar(PermissaoUsuario permissaoUsuario);
	
	public void remover(PermissaoUsuario permissaoUsuario);

}
