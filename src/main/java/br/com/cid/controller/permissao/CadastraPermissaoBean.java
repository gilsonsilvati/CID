package br.com.cid.controller.permissao;

import java.io.Serializable;

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
	
	private Repositorios repositorios = new Repositorios();
	private PermissaoUsuario permissaoUsuario;
	
	public void salvar() {
		GestaoPermissoes gestaoPermissoes = new GestaoPermissoes(this.repositorios.getPermissoes());
		gestaoPermissoes.salvar(this.permissaoUsuario);
		
		this.permissaoUsuario = new PermissaoUsuario();
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Permissão salva com sucesso!");
	}
	
	public TipoPermissao[] getTipoPermissao() {
		return TipoPermissao.values();
	}
	
	public PermissaoUsuario getPermissaoUsuario() {
		if (this.permissaoUsuario == null) {
			permissaoUsuario = new PermissaoUsuario();
		}
		
		return permissaoUsuario;
	}
	
	public void setPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
		this.permissaoUsuario = permissaoUsuario;
		
		if (this.permissaoUsuario == null) {
			permissaoUsuario = new PermissaoUsuario();
		}
	}
	
}
