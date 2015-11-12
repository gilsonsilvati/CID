package br.com.cid.service;

import java.io.Serializable;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;

public class GestaoMedicos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Medicos medicos;
	
	public GestaoMedicos(Medicos medicos) {
		this.medicos = medicos;
	}
	
	public void salvar(Medico medico) {
		this.medicos.guardar(medico);
	}
	
	public void excluir(Medico medico) {
		this.medicos.remover(medico);
	}

}
