package br.com.cid.controller.permissao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.model.TipoPermissao;
import br.com.cid.service.GestaoPermissoes;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@RequestScoped
public class CadastraPermissaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios;
	private PermissaoUsuario permissaoUsuario;
	private List<TipoPermissao> permissoes;
	
	@PostConstruct
	public void inicializar() {
		this.repositorios = new Repositorios();
		this.limpar();
		this.permissoes = Arrays.asList(TipoPermissao.values());
	}
	
	public void salvar() {
		GestaoPermissoes gestaoPermissoes = new GestaoPermissoes(this.repositorios.getPermissoes());
		gestaoPermissoes.salvar(this.permissaoUsuario);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Permissão salva com sucesso!");
		
		this.limpar();
	}
	
	private void limpar() {
		this.permissaoUsuario = new PermissaoUsuario();
	}
	
	public List<TipoPermissao> getPermissoes() {
		return permissoes;
	}

	public PermissaoUsuario getPermissaoUsuario() {
		return permissaoUsuario;
	}
	
	public void setPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
		this.permissaoUsuario = permissaoUsuario;
		
		if (this.permissaoUsuario == null) {
			permissaoUsuario = new PermissaoUsuario();
		}
	}
	
}
