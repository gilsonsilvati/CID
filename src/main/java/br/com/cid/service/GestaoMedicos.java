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
	public void salvar(Medico medico) throws NegocioException {
		if (isExist(medico))
			throw new NegocioException("Já existe um médico registrado em nossa base de dados.");
		
		this.medicos.guardar(medico);
	}
	
	private boolean isExist(Medico medico) {
		Medico medicoSemelhante = this.medicos.comDadosIguais(medico);
		return medicoSemelhante != null && !medicoSemelhante.equals(medico);
	}
	
	@Transactional
	public void excluir(Medico medico) {
		this.medicos.remover(medico);
	}

}
