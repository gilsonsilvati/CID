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
	
	private List<PermissaoUsuario> permissoesUsuario;
	
	@Inject
	private Permissoes permissoes;
	
	@Inject
	private GestaoPermissoes gestaoPermissoes;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		permissoesUsuario = permissoes.todos();
	}
	
	public void excluir() {
		gestaoPermissoes.excluir(permissaoSelecionada);
		facesMessages.info("Permissão excluída com sucesso!");
		
		inicializar();
	}
	
	public List<PermissaoUsuario> getPermissoes() {
		return permissoesUsuario;
	}

	public PermissaoUsuario getPermissaoSelecionada() {
		return permissaoSelecionada;
	}
	
	public void setPermissaoSelecionada(PermissaoUsuario permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}
	
}
