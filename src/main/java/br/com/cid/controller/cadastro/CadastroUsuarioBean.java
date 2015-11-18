package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.cid.model.Usuario;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private Usuario usuario;
	
	@PostConstruct
	public void inicializar() {
		this.repositorios = new Repositorios();
		this.limpar();
	}
	
	public void salvar() {
		GestaoUsuarios gestaoUsuarios = new GestaoUsuarios(this.repositorios.getUsuarios());
		gestaoUsuarios.salvar(this.usuario);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Usuário salvo com sucesso!");
		
		this.limpar();
	}
	
	private void limpar() {
		this.usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
	}
	
}
