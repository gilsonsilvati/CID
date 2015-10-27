package br.com.cid.controller.cadastro;

import java.io.Serializable;

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
	
	public void cadastrar() {
		GestaoUsuarios gestaoUsuarios = new GestaoUsuarios(this.repositorios.getUsuarios());
		gestaoUsuarios.salvar(this.usuario);
		
		this.usuario = new Usuario();
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Usuário cadastrado com sucesso!");
	}
	
	public Usuario getUsuario() {
		if (this.usuario == null) {
			usuario = new Usuario();
		}
		
		return usuario;
	}

}
