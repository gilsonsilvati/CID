package br.com.cid.controller.cadastro;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	private Repositorios repositorios;
	private Medico medico;
	private List<UF> ufs;
	
	@PostConstruct
	public void inicializar() {
		this.repositorios = new Repositorios();
		this.limpar();
		this.ufs = Arrays.asList(UF.values());
	}

	public void salvar() {
		GestaoMedicos gestaoMedicos = new GestaoMedicos(this.repositorios.getMedicos());
		gestaoMedicos.salvar(this.medico);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Médico salvo com sucesso!");
		
		this.limpar();
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
	
}
