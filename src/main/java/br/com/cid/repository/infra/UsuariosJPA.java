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
		List<Usuario> todosUsuarios = manager.createQuery("from Usuario order by nome",
				Usuario.class).getResultList();
		return todosUsuarios;
	}

	@Override
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	@Override
	public Usuario porNome(String nome) {
		return this.manager.find(Usuario.class, nome);
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
