package br.com.cid.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.model.Doenca;
import br.com.cid.repository.DoencaRepository;

@ManagedBean
@ViewScoped
public class ConsultaDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Doenca> doencas;
	
	public List<Doenca> getMedicos() {
		if (this.doencas == null) {
			EntityManager manager = this.getEntityManager();
			DoencaRepository repository = new DoencaRepository(manager);
			
			this.doencas = repository.buscaTodos();
			
			manager.close();
		}
		
		return doencas;
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}

}
