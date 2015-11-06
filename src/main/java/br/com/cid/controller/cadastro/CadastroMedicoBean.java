package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;
import br.com.cid.service.GestaoMedicos;
import br.com.cid.util.FacesMessageUtil;
import br.com.cid.util.Repositorios;

@ManagedBean
@RequestScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private Medico medico;
	
	public void salvar() {
		GestaoMedicos gestaoMedicos = new GestaoMedicos(this.repositorios.getMedicos());
		gestaoMedicos.salvar(this.medico);
		
		this.medico = new Medico();

		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Médico salvo com sucesso!");
	}
	
	public UF[] getUf() {
		return UF.values();
	}

	public Medico getMedico() {
		if (this.medico == null) {
			medico = new Medico();
		}
		
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
		
		if (this.medico == null) {
			medico = new Medico();
		}
	}
	
}
