package br.com.cid.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medico.class)
public abstract class Medico_ {

	public static volatile SingularAttribute<Medico, Date> dataModificacao;
	public static volatile SingularAttribute<Medico, UF> sigla;
	public static volatile SingularAttribute<Medico, String> especialidade;
	public static volatile SingularAttribute<Medico, String> nome;
	public static volatile SingularAttribute<Medico, Long> id;
	public static volatile SingularAttribute<Medico, Date> dataCriacao;
	public static volatile SingularAttribute<Medico, String> email;
	public static volatile SingularAttribute<Medico, Integer> crm;

}

