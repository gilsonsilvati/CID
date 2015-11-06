package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;
import br.com.cid.service.GestaoMedicos;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@ViewScoped
public class ConsultaMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private List<Medico> medicos = new ArrayList<>();
	private Medico medicoSelecionado;
	
	@PostConstruct
	public void inicializar() {
		Medicos medicos = this.repositorios.getMedicos();
		this.medicos = medicos.todos();
	}
	
	public void excluir() {
		GestaoMedicos gestaoMedicos = new GestaoMedicos(this.repositorios.getMedicos());
		gestaoMedicos.excluir(medicoSelecionado);
		
		this.inicializar();
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Médico excluído com sucesso!");
	}
	
	public List<Medico> getMedicos() {
		return medicos;
	}

	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}

	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}
	
}
