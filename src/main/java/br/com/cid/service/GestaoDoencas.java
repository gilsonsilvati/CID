package br.com.cid.service;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

public class GestaoDoencas {
	
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
