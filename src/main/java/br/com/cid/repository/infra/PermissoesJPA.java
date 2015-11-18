package br.com.cid.repository.infra;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;

public class PermissoesJPA implements Permissoes {

	private EntityManager manager;
	
	public PermissoesJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<PermissaoUsuario> todos() {
		List<PermissaoUsuario> todasPermissoes = manager.createNamedQuery("PermissaoUsuario.buscarTodos",
				PermissaoUsuario.class).getResultList();
		
		return todasPermissoes;
	}

	@Override
	public PermissaoUsuario porId(Long id) {
		return this.manager.find(PermissaoUsuario.class, id);
	}

	@Override
	public List<String> porEmail(String email) {
		List<String> todosPorEmail = manager.createNamedQuery("PermissaoUsuario.buscarPorEmail",
				String.class).setParameter("email", email).getResultList();
		
		return todosPorEmail;
	}

	@Override
	public void remover(PermissaoUsuario permissaoUsuario) {
		this.manager.remove(manager.getReference(PermissaoUsuario.class, permissaoUsuario.getId()));
	}

	@Override
	public PermissaoUsuario guardar(PermissaoUsuario permissaoUsuario) {
		return this.manager.merge(permissaoUsuario);
	}

	@Override
	public PermissaoUsuario comDadosIguais(PermissaoUsuario permissaoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
