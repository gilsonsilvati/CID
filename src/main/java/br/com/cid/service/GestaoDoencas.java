package br.com.cid.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;
import br.com.cid.util.jpa.Transactional;

public class GestaoDoencas implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Doencas doencas;
	
	@Transactional
	public void salvar(Doenca doenca) {
		this.doencas.guardar(doenca);
	}
	
	@Transactional
	public void excluir(Doenca doenca) {
		this.doencas.remove(doenca);
	}

}
