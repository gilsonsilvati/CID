package br.com.cid.controller.login;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.cid.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
public class LoginUsuarioBean {
	
	private String email;
	private String senha;
	
	public String logar() {
		try {
			this.getRequest().login(this.email, this.senha);
			return "/paginas/Home?faces-redirect=true";
		} catch (ServletException e) {
			FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
					"E-mail ou Senha não confere.");
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
