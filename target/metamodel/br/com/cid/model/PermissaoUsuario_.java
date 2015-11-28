package br.com.cid.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PermissaoUsuario.class)
public abstract class PermissaoUsuario_ {

	public static volatile SingularAttribute<PermissaoUsuario, Date> dataModificacao;
	public static volatile SingularAttribute<PermissaoUsuario, Usuario> usuario;
	public static volatile SingularAttribute<PermissaoUsuario, Long> id;
	public static volatile SingularAttribute<PermissaoUsuario, TipoPermissao> permissao;
	public static volatile SingularAttribute<PermissaoUsuario, Date> dataCriacao;
	public static volatile SingularAttribute<PermissaoUsuario, String> email;

}

