package br.com.cid.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

public class DoencasJPA implements Doencas, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Override
	public List<Doenca> todos() {
		List<Doenca> todasDoencas = manager.createNamedQuery("Doenca.buscarTodos", Doenca.class)
				.getResultList();
		
		return todasDoencas;
	}

	@Override
	public Doenca porId(Long id) {
		return this.manager.getReference(Doenca.class, id);
	}

	@Override
	public List<Doenca> porCID(String cid) {
		List<Doenca> todasPorCid =  manager.createNamedQuery("Doenca.buscarPorCID", Doenca.class)
				.setParameter("cid", cid)
				.getResultList();
		
		return todasPorCid;
	}

	@Override
	public Doenca guardar(Doenca doenca) {
		return this.manager.merge(doenca);
	}

	@Override
	public void remove(Doenca doenca) {
		this.manager.remove(manager.getReference(Doenca.class, doenca.getId()));
	}
	
	// Lazy
	@Override
	public List<Doenca> buscarComPaginacao(int first, int pageSize) {
		return manager.createNamedQuery("Doenca.buscarTodos", Doenca.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	// Lazy
	@Override
	public Long encontrarQuantidadeDeDoencas() {
		return manager.createQuery("select count(d) from Doenca d", Long.class).getSingleResult();
	}

	@Override
	public List<Doenca> criteriaPorCIDLike(String cid) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Doenca> criteriaQuery = builder.createQuery(Doenca.class);
		
		Root<Doenca> d = criteriaQuery.from(Doenca.class);
		criteriaQuery.select(d);
		criteriaQuery.where(builder.like(d.get("cid"), cid));
		
		TypedQuery<Doenca> query = manager.createQuery(criteriaQuery);
		List<Doenca> doencasCID = query.getResultList();
		
		return doencasCID;
	}

}
