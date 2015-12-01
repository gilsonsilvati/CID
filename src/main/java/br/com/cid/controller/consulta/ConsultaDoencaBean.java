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
	
	private Doenca doencaSelecionada;
	private Doenca doenca;
	
	@Inject
	private Doencas doencas;
	
	@Inject
	private GestaoDoencas gestaoDoencas;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		this.todasDoencas = doencas.todos();
		this.doenca = new Doenca();
	}
	
	public void excluir() {
		this.gestaoDoencas.excluir(this.doencaSelecionada);
		this.facesMessages.info("cid " + this.doencaSelecionada.getCid() + " excluído com sucesso!");
		
		inicializar();
	}
	
	public void pesquisar() {
		this.todasDoencas = doencas.porCID(this.doenca.getCid());
		this.facesMessages.info("resultado da pesquisa!");
	}
	
	public void pesquisaCriteria() {
		this.todasDoencas = doencas.criteriaPorCIDLike(this.doenca.getCid());
		this.facesMessages.info("Resultado da pesquisa!");
	}

	public List<Doenca> getTodasDoencas() {
		return todasDoencas;
	}

	public Doenca getDoencaSelecionada() {
		return doencaSelecionada;
	}

	public void setDoencaSelecionada(Doenca doencaSelecionada) {
		this.doencaSelecionada = doencaSelecionada;
	}

	public Doenca getDoenca() {
		return doenca;
	}
	
}
