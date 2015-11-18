package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.cid.model.Doenca;
import br.com.cid.service.GestaoDoencas;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@RequestScoped
public class CadastroDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios;
	private Doenca doenca;
	
	@PostConstruct
	public void inicializar() {
		this.repositorios = new Repositorios();
		this.limpar();
	}
	
	public void salvar() {
		GestaoDoencas gestaoDoencas = new GestaoDoencas(this.repositorios.getDoencas());
		gestaoDoencas.salvar(this.doenca);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Doença salva com sucesso!");
		
		this.limpar();
	}
	
	private void limpar() {
		this.doenca = new Doenca();
	}
	
	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
		
		if (this.doenca == null) {
			doenca = new Doenca();
		}
	}
	
}
