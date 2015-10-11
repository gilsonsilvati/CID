package br.com.cid.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.model.Usuario;
import br.com.cid.repository.UsuarioRepository;

@ManagedBean
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public void adicionaUsuario() {
		EntityManager manager = this.getEntityManager();
		UsuarioRepository repository = new UsuarioRepository(manager);

		repository.adiciona(this.usuario);

		/* Mensagem */
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usuário cadastrado com sucesso!");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, mensagem);

		manager.close();

		this.usuario = new Usuario();
		
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}

	public Usuario getUsuario() {
		if (this.usuario == null) {
			usuario = new Usuario();
		}
		
		return usuario;
	}

}