package br.com.cid.modelLazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

public class LazyDoencaDataModel extends LazyDataModel<Doenca> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Doencas doencas;
	
	public LazyDoencaDataModel(Doencas doencas) {
		this.doencas = doencas;
	}

	@Override
	public List<Doenca> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<Doenca> doencas = this.doencas.buscarComPaginacao(first, pageSize);

		this.setRowCount(this.doencas.encontrarQuantidadeDeDoencas().intValue());
		
		return doencas;
	}

}
