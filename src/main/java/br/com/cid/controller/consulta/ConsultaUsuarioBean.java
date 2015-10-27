package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@ViewScoped
public class ConsultaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario usuarioSelecionado;
	
	@PostConstruct
	public void inicializar() {
		Usuarios usuarios = this.repositorios.getUsuarios();
		this.usuarios = usuarios.todos();
	}
	
	public void excluir() {
		GestaoUsuarios gestaoUsuarios = new GestaoUsuarios(this.repositorios.getUsuarios());
		gestaoUsuarios.excluir(usuarioSelecionado);
		
		this.inicializar();
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Usuário excluído com sucesso!");
	}
	
	public void editar() {
		
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
}
