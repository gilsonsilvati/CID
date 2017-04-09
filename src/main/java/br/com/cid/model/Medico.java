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

@NamedQuery(name = "Medico.buscarPorCRM", query = "select m from Medico m where m.crm = :crm")
@Entity
@Table(name = "medico", indexes = {@Index(columnList = "crm", unique = true)})
public class Medico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 100, nullable = false)
	private String nome;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	private String especialidade;
	
	@NotNull
	@Column(length = 10, nullable = false)
	private Integer crm;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 2, nullable = false)
	private UF sigla;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	@Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message = "é inválido!")
	private String email;
	
	/* Para fazer auditoria... */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false, updatable = false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_modificacao", nullable = false, updatable = true)
	private Date dataModificacao;
	
	// TODO: Adicionar version...
	
	public Medico() {
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade.toUpperCase();
	}

	public Integer getCrm() {
		return crm;
	}
	public void setCrm(Integer crm) {
		this.crm = crm;
	}

	public UF getSigla() {
		return sigla;
	}
	public void setSigla(UF sigla) {
		this.sigla = sigla;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim().toUpperCase();
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
		Medico other = (Medico) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}