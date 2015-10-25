package br.com.cid.controller.cadastro;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;
import br.com.cid.repository.MedicoRepository;
import br.com.cid.util.EntityManagerUtil;
import br.com.cid.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	
	public void adiciona() {
		EntityManager manager = EntityManagerUtil.getEntityManager();
		MedicoRepository repository = new MedicoRepository(manager);
		
		repository.adiciona(this.medico);
		
		FacesMessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Médico cadastrado com sucesso!");
		
		manager.close();
		
		this.medico = new Medico();
		
	}
	
	public UF[] getUf() {
		return UF.values();
	}

	public Medico getMedico() {
		if (this.medico == null) {
			medico = new Medico();
		}
		
		return medico;
	}
	
}
