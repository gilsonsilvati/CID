package br.com.cid.controller.permissao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.model.TipoPermissao;
import br.com.cid.service.GestaoPermissoes;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastraPermissaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PermissaoUsuario permissaoUsuario;
	
	private List<TipoPermissao> permissoes;
	
	@Inject
	private GestaoPermissoes gestaoPermissoes;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		if (this.permissaoUsuario == null) {
			this.limpar();
		}
		
		this.permissoes = Arrays.asList(TipoPermissao.values());
	}
	
	public void salvar() {
		this.gestaoPermissoes.salvar(this.permissaoUsuario);
		this.facesMessages.info("Permissão salva com sucesso!");
		
		limpar();
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
	
	public boolean isEditando() {
		return this.permissaoUsuario.getId() != null;
	}
	
}
