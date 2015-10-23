package br.com.cid.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.model.Medico;
import br.com.cid.repository.MedicoRepository;

@ManagedBean
@ViewScoped
public class ConsultaMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Medico> medicos;
	
	public List<Medico> getMedicos() {
		if (this.medicos == null) {
			EntityManager manager = this.getEntityManager();
			MedicoRepository repository = new MedicoRepository(manager);
			
			this.medicos = repository.buscaTodos();
			
			manager.close();
		}
		
		return medicos;
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}

}
