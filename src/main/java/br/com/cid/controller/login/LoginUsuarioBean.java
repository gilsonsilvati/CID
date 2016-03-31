package br.com.cid.controller.login;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.util.jsf.FacesMessages;

@Named
@RequestScoped
public class LoginUsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	@Inject
	private FacesMessages facesMessages;
	
	public String logar() {
		try {
			if (email.equals("") || senha.equals("")) {
				facesMessages.error(facesMessages.getMensagemI18n("login_empty"));
				
				return null;
			} else {
				this.getRequest().login(this.email, this.senha);
				return "/paginas/Home?faces-redirect=true";
			}
			
		} catch (ServletException e) {
			facesMessages.error(facesMessages.getMensagemI18n("erro_login"));
			
			return null;
		}
	}
	
	public String logout() throws ServletException {
		this.getRequest().logout();
		return "/Login?faces-redirect=true";
	}
	
	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
