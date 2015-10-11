package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.Doenca;

public class DoencaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public DoencaRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(Doenca doenca) {
		this.manager.persist(doenca);
	}

	public void remove(Doenca doenca) {
		this.manager.remove(doenca);
	}

	public void removePorId(Long id) {
		Doenca doenca = this.buscaPorId(id);
		this.remove(doenca);

	}

	public Doenca atualiza(Doenca doenca) {
		return this.manager.merge(doenca);

	}

	public List<Doenca> buscaTodos() {
		List<Doenca> todasDoencas = manager.createQuery("from Doenca order by doenca",
				Doenca.class).getResultList();
		return todasDoencas;
	}

	public Doenca buscaPorId(Long id) {
		return this.manager.find(Doenca.class, id);

	}

	public Doenca buscaPorCID(String cid) {
		return this.manager.find(Doenca.class, cid);

	}

}
