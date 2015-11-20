package br.com.cid.repository.infra;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

public class DoencasJPA implements Doencas {

	private EntityManager manager;
	
	public DoencasJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Doenca> todos() {
		List<Doenca> todasDoencas = manager.createNamedQuery("Doenca.buscarTodos", Doenca.class)
				.getResultList();
		
		return todasDoencas;
	}

	@Override
	public Doenca porId(Long id) {
		return this.manager.find(Doenca.class, id);
	}

	@Override
	public List<String> porCID(String cid) {
		List<String> todasPorCid = manager.createNamedQuery("Doenca.buscarPorCID", String.class)
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

	@Override
	public List<Doenca> buscarComPaginacao(int first, int pageSize) {
		return manager.createNamedQuery("Doenca.buscarTodos", Doenca.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	@Override
	public Long encontrarQuantidadeDeDoencas() {
		return manager.createQuery("select count(d) from Doenca d", Long.class).getSingleResult();
	}

}
