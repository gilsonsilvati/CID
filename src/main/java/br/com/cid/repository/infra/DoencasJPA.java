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
		List<Doenca> todasDoencas = manager.createQuery("from Doenca order by doenca",
				Doenca.class).getResultList();
		return todasDoencas;
	}

	@Override
	public Doenca porId(Long id) {
		return this.manager.find(Doenca.class, id);
	}

	@Override
	public Doenca porCID(String cid) {
		return this.manager.find(Doenca.class, cid);
	}

	@Override
	public Doenca guardar(Doenca doenca) {
		return this.manager.merge(doenca);
	}

	@Override
	public void remove(Doenca doenca) {
		this.manager.remove(manager.getReference(Doenca.class, doenca.getId()));
	}

}
