package br.com.cid.controller.relatorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;
import br.com.cid.util.Repositorios;

@ManagedBean
@ViewScoped
public class ImprimiDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private List<Doenca> doencas = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		Doencas doencas = this.repositorios.getDoencas();
		this.doencas = doencas.todos();
	}
	
	public List<Doenca> getDoencas() {
		return doencas;
	}
	
}
