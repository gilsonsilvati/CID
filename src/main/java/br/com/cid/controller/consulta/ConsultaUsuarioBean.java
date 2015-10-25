package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.cid.model.Usuario;
import br.com.cid.repository.UsuarioRepository;
import br.com.cid.util.EntityManagerUtil;

@ManagedBean
@ViewScoped
public class ConsultaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
	
	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			EntityManager manager = EntityManagerUtil.getEntityManager();
			UsuarioRepository repository = new UsuarioRepository(manager);
			
			this.usuarios = repository.buscaTodos();
			
			manager.close();
		}
		
		return usuarios;
		
	}
	
	public void excluir() {
		

	}
	
	public void editar() {
		
		
	}
	
}
