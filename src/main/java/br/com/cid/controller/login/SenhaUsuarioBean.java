package br.com.cid.controller.login;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.br.CPF;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.service.NegocioException;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class SenhaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	@CPF
	private String cpf;

	@Inject
	private Usuarios usuarios;

	@Inject
	private GestaoUsuarios gestaoUsuarios;

	@Inject
	private FacesMessages facesMessages;
	
	public void pesquisar() {
		usuario = usuarios.buscaPorCPF(cpf);

		if (usuario != null) {
			facesMessages.info("Usuário " + usuario.getNome() + " localizado.");
		} else {
			facesMessages.info("Usuário não localizado.");
		}
	}
	
	public void salvar() {
		try {
			gestaoUsuarios.salvar(usuario);
			facesMessages.info("usuário " + usuario.getNome() + " salvo com sucesso!");
			limpar();
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			facesMessages.error("Falha ao cadastrar usuário.");
		}
	}

	private void limpar() {
		usuario = null;
		cpf = "";
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
