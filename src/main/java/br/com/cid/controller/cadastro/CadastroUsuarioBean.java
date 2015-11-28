package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Usuario;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	@Inject
	private GestaoUsuarios gestaoUsuarios;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		if (this.usuario == null) {
			this.limpar();
		}
	}
	
	public void salvar() {
		this.gestaoUsuarios.salvar(this.usuario);
		this.facesMessages.info("Usuário " + this.usuario.getNome() + " salvo com sucesso!");
		
		limpar();
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
	
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
	
}
