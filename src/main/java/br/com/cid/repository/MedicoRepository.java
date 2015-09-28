package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;

public class MedicoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public MedicoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Medico medico) {
		this.manager.persist(medico);
	}
	
	public Medico atualiza(Medico medico) {
		return this.manager.merge(medico);
	}
	
	public void remove(Medico medico) {
		this.manager.remove(medico);
	}
	
	public void removePorId(Long id) {
		Medico medico = this.buscaPorId(id);
		remove(medico);
	}
	
	public List<Medico> buscaPorUF(UF uf) {
		List<Medico> medicosPorUF = manager.createQuery("from Medico m where m.uf = :uf", Medico.class)
									.setParameter("uf", uf)
									.getResultList();
		return medicosPorUF;
	}
	
	public Medico buscaPorId(Long id) {
		Medico medico = manager.find(Medico.class, id);
		return medico;
	}
	
	public List<Medico> buscaTodos() {
		List<Medico> todosMedicos = manager.createQuery("from Medico order by nome",
				Medico.class).getResultList();
		return todosMedicos;
	}

}
