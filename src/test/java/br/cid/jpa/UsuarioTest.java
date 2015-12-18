/*
package br.cid.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.cid.model.Usuario;

public class UsuarioTest {
	
	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	
	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("cidPU");
	}
	
	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		if (this.manager.isOpen()) {
			this.manager.close();
		}
	}
	
	@Test
	public void exemploOrdenacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
		
		Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
		Order order = builder.desc(usuario.get("nome"));
		
		criteriaQuery.select(usuario);
		criteriaQuery.orderBy(order);
		
		TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
		List<Usuario> usuarios = query.getResultList();
		
		for (Usuario u : usuarios) {
			System.out.println("\nNome: " + u.getNome() + " - E-mail: " + u.getEmail());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeNativeQuery() {
		//Query query = this.manager.createNativeQuery("select c.placa, m.descricao from Carro c, ModeloCarro m where c.codigo_modelo = m.codigo");
		//List<CarModelTemp> resultado = query.getResultList();
		
		Query query = this.manager.createNativeQuery("select * from usuario", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		for (Usuario u : usuarios) {
			System.out.println("\nNome: " + u.getNome());
			System.out.println("CPF: " + u.getCpf());
			System.out.println("Senha: " + u.getSenha());
		}
	}

}
*/
