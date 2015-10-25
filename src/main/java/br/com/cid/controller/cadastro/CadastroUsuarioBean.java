package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.cid.model.Usuario;
import br.com.cid.repository.UsuarioRepository;
import br.com.cid.util.EntityManagerUtil;
import br.com.cid.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public void adicionaUsuario() {
		EntityManager manager = EntityManagerUtil.getEntityManager();
		UsuarioRepository repository = new UsuarioRepository(manager);

		repository.adiciona(this.usuario);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!");

		manager.close();

		this.usuario = new Usuario();
		
	}
	
	public Usuario getUsuario() {
		if (this.usuario == null) {
			usuario = new Usuario();
		}
		
		return usuario;
	}

}
