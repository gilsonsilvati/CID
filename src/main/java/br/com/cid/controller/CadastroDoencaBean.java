package br.com.cid.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.model.Doenca;
import br.com.cid.repository.DoencaRepository;

@ManagedBean
@RequestScoped
public class CadastroDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Doenca doenca;
	
	public void adicionaDoenca() {
		EntityManager manager = this.getEntityManager();
		DoencaRepository repository = new DoencaRepository(manager);

		repository.adiciona(this.doenca);

		/* Mensagem */
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Doença cadastrada com sucesso!");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, mensagem);

		manager.close();

		this.doenca = new Doenca();
		
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}

	public Doenca getDoenca() {
		if (this.doenca == null) {
			doenca = new Doenca();
		}
		
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}
	
}
