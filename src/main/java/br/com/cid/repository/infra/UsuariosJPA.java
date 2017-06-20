package br.com.cid.repository.infra;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;

public class UsuariosJPA implements Usuarios {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> todos() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		
		return criteria.list();
	}

	@Override
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> porCPF(String cpf) {
		String query = "from Usuario where cpf like :cpf "
				+ "order by nome";
		
		return manager.createQuery(query, Usuario.class)
				.setParameter("cpf", "%" + StringUtils.defaultIfBlank(cpf, "") + "%")
				.getResultList();
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
		try {
			String query = "from Usuario where cpf like :cpf";
			
			usuario = manager.createQuery(query, Usuario.class)
					.setParameter("cpf", "%" + StringUtils.defaultIfBlank(usuario.getCpf(), "") + "%")
					.getSingleResult();
			
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Usuario buscaPorCPF(String cpf) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("cpf", cpf));
		
		return (Usuario) criteria.uniqueResult();
	}
	
}
