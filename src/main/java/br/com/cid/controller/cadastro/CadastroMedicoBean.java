package br.com.cid.controller.cadastro;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;
import br.com.cid.service.GestaoMedicos;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	
	private List<UF> ufs;
	
	@Inject
	private GestaoMedicos gestaoMedicos;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		if (this.medico == null) {
			this.limpar();
		}
		
		this.ufs = Arrays.asList(UF.values());
	}

	public void salvar() {
		this.gestaoMedicos.salvar(this.medico);
		this.facesMessages.info("Médico " + this.medico.getNome() + " salvo com sucesso!");
		
		limpar();
	}
	
	private void limpar() {
		this.medico = new Medico();
	}
	
	public List<UF> getUfs() {
		return ufs;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
		
		if (this.medico == null) {
			medico = new Medico();
		}
	}
	
	public boolean isEditando() {
		return this.medico.getId() != null;
	}
	
}
