package br.com.cid.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;

public class MedicosJPA implements Medicos, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Override
	public List<Medico> todos() {
		List<Medico> todosMedicos = manager.createNamedQuery("Medico.buscarTodos", Medico.class)
				.getResultList();
		
		return todosMedicos;
	}

	@Override
	public Medico porId(Long id) {
		Medico medico = manager.find(Medico.class, id);
		return medico;
	}

	@Override
	public List<String> porNome(String nome) {
		List<String> todosPorNome = manager.createNamedQuery("Medico.buscarPorNome", String.class)
				.setParameter("nome", nome)
				.getResultList();
		
		return todosPorNome;
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
