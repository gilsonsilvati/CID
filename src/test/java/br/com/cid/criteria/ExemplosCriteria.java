package br.com.cid.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.cid.model.Doenca;
import br.com.cid.model.Doenca_;
import br.com.cid.model.PermissaoUsuario;
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
	
	@SuppressWarnings("unused")
	@Test
	public void exemploFuncao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Doenca> criteriaQuery = builder.createQuery(Doenca.class);
		
		Root<Doenca> doenca = criteriaQuery.from(Doenca.class);
		Predicate predicate = builder.equal(builder.<String>upper(doenca.get("cid")),
				"n13".toUpperCase());
		
		criteriaQuery.select(doenca);
		criteriaQuery.where(predicate);
		
		TypedQuery<Doenca> query = manager.createQuery(criteriaQuery);
		List<Doenca> doencas = query.getResultList();
		
		for (Doenca d : doencas) {
			System.out.println(d.getCid() + " - " + d.getDoenca());
		}
	}
	
	@Test
	public void exemploOrdenacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Doenca> criteriaQuery = builder.createQuery(Doenca.class);
		
		Root<Doenca> doenca = criteriaQuery.from(Doenca.class);
		Order order = builder.desc(doenca.get(Doenca_.cid));
		
		criteriaQuery.select(doenca);
		criteriaQuery.orderBy(order);
		
		TypedQuery<Doenca> query = manager.createQuery(criteriaQuery);
		List<Doenca> doencas = query.getResultList();
		
		for (Doenca d : doencas) {
			System.out.println(d.getCid() + " - " + d.getDoenca());
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Test
	public void exemploJoinEFetch() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
		
		Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
		Join<Usuario, PermissaoUsuario> permissoes = (Join) usuario.fetch("permissoes");
		
		TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
		List<Usuario> usuarios = query.getResultList();
		
		for (Usuario u : usuarios) {
			System.out.println(u.getEmail() + " - " + u.getPermissoes());
		}
	}
	
	@Test
	public void dfdsfsfsd() {
		
	}

}
