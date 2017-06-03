package br.com.cid.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

public class GestaoDoencas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Doencas doencas;
	
	@Transactional
	public void salvar(Doenca doenca) throws NegocioException {
		if (isExist(doenca))
			throw new NegocioException("Já existe uma doença registrada em nossa base de dados.");
		
		this.doencas.guardar(doenca);
	}
	
	private boolean isExist(Doenca doenca) {
		Doenca doencaSemelhante = this.doencas.comDadosIguais(doenca);
		return doencaSemelhante != null && !doencaSemelhante.equals(doenca);
	}
	
	@Transactional
	public void excluir(Doenca doenca) {
		this.doencas.remove(doenca);
	}

}
