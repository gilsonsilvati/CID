package br.com.cid.repository;

import java.util.List;

import br.com.cid.model.Doenca;

public interface Doencas {

	public List<Doenca> todos();
	public Doenca porId(Long id);
	public Doenca porCID(String cid);

	public Doenca guardar(Doenca doenca);

	public void remove(Doenca doenca);

}
