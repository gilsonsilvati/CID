package br.com.cid.controller.permissao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;
import br.com.cid.service.GestaoPermissoes;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@ViewScoped
public class ConsultaPermissaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Repositorios repositorios = new Repositorios();
	private List<PermissaoUsuario> permissoes = new ArrayList<>();
	private PermissaoUsuario permissaoSelecionada;
	
	@PostConstruct
	public void inicializar() {
		Permissoes permissoes = this.repositorios.getPermissoes();
		this.permissoes = permissoes.todos();
	}
	
	public void excluir() {
		GestaoPermissoes gestaoPermissoes = new GestaoPermissoes(this.repositorios.getPermissoes());
		gestaoPermissoes.excluir(permissaoSelecionada);
		
		this.inicializar();
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Permissão excluída com sucesso!");
	}
	
	public List<PermissaoUsuario> getPermissoes() {
		return permissoes;
	}

	public PermissaoUsuario getPermissaoSelecionada() {
		return permissaoSelecionada;
	}
	
	public void setPermissaoSelecionada(PermissaoUsuario permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}
	
}
