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

import br.com.cid.model.Usuario;
import br.com.cid.repository.UsuarioRepository;

@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	
	public void adicionaUsuario() {
		EntityManager manager = this.getEntityManager();
		UsuarioRepository repository = new UsuarioRepository(manager);

		repository.adiciona(this.usuario);

		/* Mensagem */
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Cadastrado com sucesso!");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, mensagem);

		manager.close();

		this.usuario = new Usuario();
		this.usuarios = null;
		
	}
	
	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			EntityManager manager = this.getEntityManager();
			UsuarioRepository repository = new UsuarioRepository(manager);
			
			this.usuarios = repository.buscaTodos();
			
			manager.close();
		}
		
		return usuarios;
		
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
