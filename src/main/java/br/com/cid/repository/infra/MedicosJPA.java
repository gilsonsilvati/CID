package br.com.cid.repository.infra;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;

public class MedicosJPA implements Medicos {

	private EntityManager manager;
	
	public MedicosJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Medico> todos() {
		List<Medico> todosMedicos = manager.createQuery("from Medico order by nome",
				Medico.class).getResultList();
		return todosMedicos;
	}

	@Override
	public Medico porId(Long id) {
		Medico medico = manager.find(Medico.class, id);
		return medico;
	}

	//TODO implements...
	@Override
	public Medico porNome(String nome) {
		return manager.find(Medico.class, nome);
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
