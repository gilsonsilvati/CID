package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.UF;

public class UFRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public UFRepository(EntityManager manager) {
		this.manager = manager;
		
	}
	
	public List<UF> buscaTodos() {
		List<UF> todosUFs = manager.createQuery("from UF order by sigla",
				UF.class).getResultList();
		return todosUFs;
		
	}

	public UF buscaPorId(Long id) {
		UF uf = manager.find(UF.class, id);
		return uf;
		
	}

}
