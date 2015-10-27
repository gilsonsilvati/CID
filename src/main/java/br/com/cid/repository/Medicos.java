package br.com.cid.repository;

import java.util.List;

import br.com.cid.model.Medico;

public interface Medicos {
	
	public List<Medico> todos();
	public Medico porId(Long id);
	public Medico porNome(String nome);
	
	public Medico guardar(Medico medico);
	
	public void remover(Medico medico);

}
