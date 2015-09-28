package br.com.cid.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;
import br.com.cid.repository.MedicoRepository;
import br.com.cid.repository.UFRepository;

@ManagedBean
@ViewScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	private List<Medico> medicos;
	
	private UFRepository ufRepository;
	private List<UF> ufs;
	
	public void adiciona() {
		EntityManager manager = this.getEntityManager();
		MedicoRepository repository = new MedicoRepository(manager);
		
		repository.adiciona(this.medico);
		
		/* Mensagem */
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Cadastrado com sucesso!");
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, message);
		
		manager.close();
		
		this.medico = new Medico();
		this.medicos = null;
	}
	
	public Medico getMedico() {
		if (this.medico == null) {
			medico = new Medico();
		}
		
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}
	
	public List<Medico> getMedicos() {
		if (this.medicos == null) {
			EntityManager manager = this.getEntityManager();
			MedicoRepository repository = new MedicoRepository(manager);
			
			this.medicos = repository.buscaTodos();
			
			manager.close();
		}
		
		return medicos;
	}
	
	public UFRepository getUfRepository() {
		return ufRepository;
	}
	
	public List<UF> getUfs() {
		if (this.ufs == null) {
			EntityManager manager = this.getEntityManager();
			UFRepository repository = new UFRepository(manager);
			
			this.ufs = repository.buscaTodos();
			
			manager.close();
		}
		
		return ufs;
	}
	
}
