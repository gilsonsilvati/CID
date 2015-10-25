package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.cid.model.Doenca;
import br.com.cid.repository.DoencaRepository;
import br.com.cid.util.EntityManagerUtil;
import br.com.cid.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
public class CadastroDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Doenca doenca;
	
	public void adicionaDoenca() {
		EntityManager manager = EntityManagerUtil.getEntityManager();
		DoencaRepository repository = new DoencaRepository(manager);

		repository.adiciona(this.doenca);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Doença cadastrada com sucesso!");

		manager.close();

		this.doenca = new Doenca();
		
	}
	
	public Doenca getDoenca() {
		if (this.doenca == null) {
			doenca = new Doenca();
		}
		
		return doenca;
	}
	
}
