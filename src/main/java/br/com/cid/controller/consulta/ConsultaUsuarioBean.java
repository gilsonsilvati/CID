package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class ConsultaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	private List<Usuario> todosUsuarios;
	
	private Usuario usuarioSelecionado;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private GestaoUsuarios gestaoUsuarios;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		this.todosUsuarios = usuarios.todos();
		
		this.usuario = new Usuario();
	}
	
	public void excluir() {
		this.gestaoUsuarios.excluir(this.usuarioSelecionado);
		this.facesMessages.info("Usuário " + this.usuarioSelecionado.getNome() + " excluído com sucesso!");
		
		inicializar();
	}
	
	public void pesquisar() {
		this.todosUsuarios = usuarios.porCPF(this.usuario.getCpf());
		this.facesMessages.info("Resultado da pesquisa!");
	}
	
	public List<Usuario> getUsuarios() {
		return todosUsuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
}
