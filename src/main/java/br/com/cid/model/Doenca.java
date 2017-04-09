package br.com.cid.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotBlank;

@NamedQuery(name = "Doenca.buscarPorCID", query = "select d from Doenca d where d.cid = :cid")
@Entity
@Table(name = "doenca", indexes = {@Index(columnList = "cid", unique = true)})
public class Doenca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 10, nullable = false)
	private String cid;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	private String doenca;
	
	@NotBlank
	@Column(nullable = false)
	private String panorama;

	/* Para fazer auditoria... */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false, updatable = false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_modificacao", nullable = false, updatable = true)
	private Date dataModificacao;
	
	// TODO: Adicionar version...
	
	public Doenca() {
	}

	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid.trim().toUpperCase();
	}

	public String getDoenca() {
		return doenca;
	}
	public void setDoenca(String doenca) {
		this.doenca = doenca.toUpperCase();
	}
	
	public String getPanorama() {
		return panorama;
	}
	public void setPanorama(String panorama) {
		this.panorama = panorama;
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
		Doenca other = (Doenca) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}
