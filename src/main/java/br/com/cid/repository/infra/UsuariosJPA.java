package br.com.cid.repository.infra;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;

public class UsuariosJPA implements Usuarios {
	
	private EntityManager manager;
	
	public UsuariosJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Usuario> todos() {
		List<Usuario> todosUsuarios = manager.createNamedQuery("Usuario.buscarTodos", Usuario.class)
				.getResultList();
		
		return todosUsuarios;
	}

	@Override
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	@Override
	public List<String> porNome(String nome) {
		List<String> todosPorNome = manager.createNamedQuery("Usuario.buscarPorNome", String.class)
				.setParameter("nome", nome)
				.getResultList();
		
		return todosPorNome;
	}

	@Override
	public void remover(Usuario usuario) {
		this.manager.remove(manager.getReference(Usuario.class, usuario.getId()));
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		return this.manager.merge(usuario);
	}

	@Override
	public Usuario comDadosIguais(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
