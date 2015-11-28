package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Doenca;
import br.com.cid.service.GestaoDoencas;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Doenca doenca;

	@Inject
	private GestaoDoencas gestaoDoencas;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		if (this.doenca == null) {
			this.limpar();
		}
	}
	
	public void salvar() {
		this.gestaoDoencas.salvar(this.doenca);
		this.facesMessages.info("CID " + this.doenca.getCid() + " salvo com sucesso!");
		
		limpar();
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
	
	public boolean isEditando() {
		return this.doenca.getId() != null;
	}
	
}
