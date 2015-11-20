package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;
import br.com.cid.service.GestaoDoencas;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@ViewScoped
public class ConsultaDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	
	private List<Doenca> doencas = new ArrayList<>();
	
	// private LazyDoencaDataModel lazyDoencas;
	
	private Doenca doencaSelecionada;
	
	@PostConstruct
	public void inicializar() {
		Doencas doencas = this.repositorios.getDoencas();
		
		this.doencas = doencas.todos();
		
		// lazyDoencas = new LazyDoencaDataModel(doencas);
	}
	
	public void excluir() {
		GestaoDoencas gestaoDoencas = new GestaoDoencas(this.repositorios.getDoencas());
		gestaoDoencas.excluir(doencaSelecionada);
		
		this.inicializar();
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Doença excluída com sucesso!");
	}
	
	public List<Doenca> getDoencas() {
		return doencas;
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
