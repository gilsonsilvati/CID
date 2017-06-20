package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import br.com.cid.model.Usuario;

public interface Usuarios extends Serializable {
	
	public List<Usuario> todos();
	public Usuario porId(Long id);
	public Usuario buscaPorCPF(String cpf);
	public List<Usuario> porCPF(String cpf);
	public Usuario comDadosIguais(Usuario usuario);
	
	public Usuario guardar(Usuario usuario);
	
	public void remover(Usuario usuario);
	
}
