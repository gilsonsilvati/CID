package br.com.cid.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;

public class PermissoesJPA implements Permissoes, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<PermissaoUsuario> todos() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PermissaoUsuario.class);
		
		return criteria.list();
	}

	@Override
	public PermissaoUsuario porId(Long id) {
		return this.manager.find(PermissaoUsuario.class, id);
	}

	@Override
	public List<PermissaoUsuario> porEmail(String email) {
		List<PermissaoUsuario> todosPorEmail = manager.createNamedQuery("PermissaoUsuario.buscarPorEmail",
				PermissaoUsuario.class).setParameter("email", email).getResultList();
		
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
