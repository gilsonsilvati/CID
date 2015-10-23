package br.com.cid.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;
import br.com.cid.repository.MedicoRepository;

@ManagedBean
@RequestScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	
	public void adiciona() {
		EntityManager manager = this.getEntityManager();
		MedicoRepository repository = new MedicoRepository(manager);
		
		repository.adiciona(this.medico);
		
		/* Mensagem */
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Médico cadastrado com sucesso!");
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, message);
		
		manager.close();
		
		this.medico = new Medico();
		
	}
	
	public UF[] getUFs() {
		return UF.values();
	}
	
	public Medico getMedico() {
		if (this.medico == null) {
			medico = new Medico();
		}
		
		return medico;
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}
	
}
