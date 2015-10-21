package br.com.cid.controller;

import java.io.Serializable;
import java.util.List;

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
public class ConsultaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
	
	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			EntityManager manager = this.getEntityManager();
			UsuarioRepository repository = new UsuarioRepository(manager);
			
			this.usuarios = repository.buscaTodos();
			
			manager.close();
		}
		
		return usuarios;
		
	}
	
	private EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}

}
