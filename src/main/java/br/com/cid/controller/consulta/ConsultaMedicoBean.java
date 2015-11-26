package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;
import br.com.cid.service.GestaoMedicos;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class ConsultaMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Medico> todosMedicos;
	
	private Medico medicoSelecionado;
	
	@Inject
	private Medicos medicos;
	
	@Inject
	private GestaoMedicos gestaoMedicos;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		todosMedicos = medicos.todos();
	}
	
	public void excluir() {
		gestaoMedicos.excluir(medicoSelecionado);
		facesMessages.info("Médico " + medicoSelecionado.getNome() + " excluído com sucesso!");
		
		inicializar();
	}
	
	public List<Medico> getMedicos() {
		return todosMedicos;
	}

	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}

	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}
	
}
