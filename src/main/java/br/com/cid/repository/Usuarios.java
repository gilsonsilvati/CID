package br.com.cid.repository;

import java.util.List;

import br.com.cid.model.Usuario;

public interface Usuarios {
	
	public List<Usuario> todos();
	public Usuario porId(Long id);
	public List<Usuario> pesquisar(Usuario usuario);
	public Usuario comDadosIguais(Usuario usuario);
	
	public Usuario guardar(Usuario usuario);
	
	public void remover(Usuario usuario);
	
}
