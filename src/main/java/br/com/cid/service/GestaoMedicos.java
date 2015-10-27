package br.com.cid.service;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;

public class GestaoMedicos {
	
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
