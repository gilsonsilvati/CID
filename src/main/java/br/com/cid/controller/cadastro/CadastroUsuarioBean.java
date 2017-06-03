package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Usuario;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.service.NegocioException;
import br.com.cid.util.jsf.FacesMessages;

@Named
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
//	private UploadedFile uploadFile;
	
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
		try {
			/*if (this.uploadFile != null) {
				this.usuario.setFotoPerfil(this.uploadFile.getContents());
			}*/
			
			this.gestaoUsuarios.salvar(this.usuario);
			this.facesMessages.info("usuário " + this.usuario.getNome() + " salvo com sucesso!");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			facesMessages.error("Falha ao cadastrar usuário.");
		}
		
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
	
	/*public UploadedFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}*/

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
	
}
