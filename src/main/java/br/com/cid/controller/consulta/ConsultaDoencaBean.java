package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;
import br.com.cid.service.GestaoDoencas;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class ConsultaDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Doenca> todasDoencas;
	
	// private LazyDoencaDataModel lazyDoencas;
	
	private Doenca doencaSelecionada;
	
	@Inject
	private Doencas doencas;
	
	@Inject
	private GestaoDoencas gestaoDoencas;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		todasDoencas = doencas.todos();
		
		// lazyDoencas = new LazyDoencaDataModel(doencas);
	}
	
	public void excluir() {
		gestaoDoencas.excluir(doencaSelecionada);
		facesMessages.info("CID " + doencaSelecionada.getCid() + " excluído com sucesso!");
		
		inicializar();
	}
	
	public List<Doenca> getDoencas() {
		return todasDoencas;
	}
	
	public Doenca getDoencaSelecionada() {
		return doencaSelecionada;
	}

	public void setDoencaSelecionada(Doenca doencaSelecionada) {
		this.doencaSelecionada = doencaSelecionada;
	}
	
	/*
	public LazyDoencaDataModel getLazyDoencas() {
		return lazyDoencas;
	}
	*/
	
}
