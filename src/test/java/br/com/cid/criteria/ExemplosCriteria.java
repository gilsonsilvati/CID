package br.com.cid.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.cid.model.Doenca;
import br.com.cid.model.Usuario;

public class ExemplosCriteria {
	
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
		this.manager.close();
	}
	
	@Test
	public void projecoes() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(usuario.<String>get("nome"));
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> nomes = query.getResultList();
		
		for (String nome : nomes) {
			System.out.println(nome);
		}
	}
	
	@Test
	public void resultadoConstrutores() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Doenca> criteriaQuery = builder.createQuery(Doenca.class);
		
		Root<Doenca> doenca = criteriaQuery.from(Doenca.class);
		criteriaQuery.select(builder.construct(Doenca.class, 
						doenca.get("cid"), doenca.get("doenca")));
		
		TypedQuery<Doenca> query = manager.createQuery(criteriaQuery);
		List<Doenca> doencas = query.getResultList();
		
		for (Doenca d : doencas) {
			System.out.println(d.getCid() + " - " + d.getDoenca());
		}
	}

}
