package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.Usuario;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
		
	}
	
	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);
		
	}
	
	public void remove(Usuario usuario) {
		this.manager.remove(usuario);
		
	}
	
	public void removePorId(Long id) {
		Usuario usuario = this.buscaPorId(id);
		this.remove(usuario);
		
	}
	
	public Usuario atualiza(Usuario usuario) {
		return this.manager.merge(usuario);
		
	}
	
	public List<Usuario> buscaTodos() {
		List<Usuario> todosUsuarios = manager.createQuery("from Usuario order by nome",
				Usuario.class).getResultList();
		return todosUsuarios;
	}
	
	public Usuario buscaPorId(Long id) {
		return this.manager.find(Usuario.class, id);
		
	}
	
	public Usuario buscaPorCPF(String cpf) {
		return this.manager.find(Usuario.class, cpf);
		
	}

}
