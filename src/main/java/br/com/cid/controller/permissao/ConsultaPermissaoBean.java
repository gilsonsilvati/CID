package br.com.cid.controller.permissao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;
import br.com.cid.service.GestaoPermissoes;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class ConsultaPermissaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PermissaoUsuario permissaoSelecionada;
	private PermissaoUsuario permissao;
	
	private List<PermissaoUsuario> permissoesUsuario;
	
	@Inject
	private Permissoes permissoes;
	
	@Inject
	private GestaoPermissoes gestaoPermissoes;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		this.permissoesUsuario = permissoes.todos();
		this.permissao = new PermissaoUsuario();
	}
	
	public void excluir() {
		this.gestaoPermissoes.excluir(this.permissaoSelecionada);
		this.facesMessages.info("permissão excluída com sucesso!");
		
		inicializar();
	}
	
	public void pesquisar() {
		this.permissoesUsuario = permissoes.porEmail(this.permissao.getEmail());
		this.facesMessages.info("resultado da pesquisa!");
	}
	
	public PermissaoUsuario getPermissao() {
		return permissao;
	}

	public List<PermissaoUsuario> getPermissoesUsuario() {
		return permissoesUsuario;
	}

	public PermissaoUsuario getPermissaoSelecionada() {
		return permissaoSelecionada;
	}
	
	public void setPermissaoSelecionada(PermissaoUsuario permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}
	
}
