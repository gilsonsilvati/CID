package br.com.cid.repository.infra;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;

public class MedicosJPA implements Medicos {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Medico> todos() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Medico.class);
		
		return criteria.list();
	}

	@Override
	public Medico porId(Long id) {
		return this.manager.find(Medico.class, id);
	}

	@Override
	public List<Medico> porCRM(Integer crm) {
		List<Medico> todosPorCRM = manager.createNamedQuery("Medico.buscarPorCRM", Medico.class)
				.setParameter("crm", crm)
				.getResultList();
		
		return todosPorCRM;
	}

	@Override
	public Medico guardar(Medico medico) {
		return this.manager.merge(medico);
	}

	@Override
	public void remover(Medico medico) {
		this.manager.remove(manager.getReference(Medico.class, medico.getId()));
	}

}
