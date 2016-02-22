package br.com.cid.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;

public class GestaoMedicos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Medicos medicos;
	
	@Transactional
	public void salvar(Medico medico) {
		this.medicos.guardar(medico);
	}
	
	@Transactional
	public void excluir(Medico medico) {
		this.medicos.remover(medico);
	}

}
