package br.com.cid.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotBlank;

@NamedQuery(name = "PermissaoUsuario.buscarPorEmail", query = "select pu from PermissaoUsuario pu " +
		"where pu.email = :email")
@Entity
@Table(name = "permissao_usuario", indexes = {@Index(columnList = "email", unique = true)})
public class PermissaoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	@Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message = "é inválido!")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 10, nullable = false)
	private TipoPermissao permissao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_usuario", nullable = false)
	private Usuario usuario;
	
	/* Para fazer auditoria... */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false, updatable = false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_modificacao", nullable = false, updatable = true)
	private Date dataModificacao;
	
	// TODO: Adicionar version...
	
	public PermissaoUsuario() {
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPermissao getPermissao() {
		return permissao;
	}
	public void setPermissao(TipoPermissao permissao) {
		this.permissao = permissao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	/* Auditoria... */
	@PrePersist
	@PreUpdate
	public void configuraDatasCriacaoAlteracao() {
		this.dataModificacao = new Date();

		if (this.dataCriacao == null) {
			this.dataCriacao = new Date();
		}
	}
	
	/* hashCode e equals personalizado */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		PermissaoUsuario other = (PermissaoUsuario) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}
	
}
