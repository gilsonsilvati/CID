package br.com.cid.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.cid.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/Login?faces-redirect=true";
		
	}

	public Usuario getUsuario() {
		if (this.usuario == null) {
			usuario = new Usuario();
		}
		
		return usuario;
	}

}
