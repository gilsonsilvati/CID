package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.cid.model.Doenca;
import br.com.cid.repository.DoencaRepository;
import br.com.cid.util.EntityManagerUtil;

@ManagedBean
@ViewScoped
public class ConsultaDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Doenca> doencas;
	
	public List<Doenca> getDoencas() {
		if (this.doencas == null) {
			EntityManager manager = EntityManagerUtil.getEntityManager();
			DoencaRepository repository = new DoencaRepository(manager);
			
			this.doencas = repository.buscaTodos();
			
			manager.close();
		}
		
		return doencas;
	}
	
	public void excluir() {
		
		
	}
	
	public void editar() {
		
		
	}
	
}
