package br.com.cid.repository;

import java.io.Serializable;
import java.util.List;

import br.com.cid.model.Medico;

public interface Medicos extends Serializable {
	
	public List<Medico> todos();
	public Medico porId(Long id);
	public List<Medico> porCRM(Integer crm);
	public Medico comDadosIguais(Medico medico);
	
	public Medico guardar(Medico medico);
	
	public void remover(Medico medico);

}
