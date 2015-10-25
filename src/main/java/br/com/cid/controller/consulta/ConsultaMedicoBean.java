package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.cid.model.Medico;
import br.com.cid.repository.MedicoRepository;
import br.com.cid.util.EntityManagerUtil;

@ManagedBean
@ViewScoped
public class ConsultaMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Medico> medicos;
	
	public List<Medico> getMedicos() {
		if (this.medicos == null) {
			EntityManager manager = EntityManagerUtil.getEntityManager();
			MedicoRepository repository = new MedicoRepository(manager);
			
			this.medicos = repository.buscaTodos();
			
			manager.close();
		}
		
		return medicos;
	}
	
	public void excluir() {
		
		
	}
	
	public void editar() {
		
		
	}

}
