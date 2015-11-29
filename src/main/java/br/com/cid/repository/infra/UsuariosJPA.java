package br.com.cid.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;

public class UsuariosJPA implements Usuarios, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
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
	public List<Usuario> porCPF(String cpf) {
		List<Usuario> todosPorCPF = manager.createNamedQuery("Usuario.buscarPorCPF", Usuario.class)
				.setParameter("cpf", cpf)
				.getResultList();
		
		return todosPorCPF;
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
