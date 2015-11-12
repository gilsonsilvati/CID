package br.com.cid.service;

import java.io.Serializable;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

public class GestaoDoencas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Doencas doencas;
	
	public GestaoDoencas(Doencas doencas) {
		this.doencas = doencas;
	}
	
	public void salvar(Doenca doenca) {
		this.doencas.guardar(doenca);
	}
	
	public void excluir(Doenca doenca) {
		this.doencas.remove(doenca);
	}

}
