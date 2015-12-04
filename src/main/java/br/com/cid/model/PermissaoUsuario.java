package br.com.cid.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "permissao_usuario")
@NamedQueries({
	@NamedQuery(name = "PermissaoUsuario.buscarTodos", query = "select pu from PermissaoUsuario pu"),
	@NamedQuery(name = "PermissaoUsuario.buscarPorEmail", query = "select pu from PermissaoUsuario pu where pu.email = :email")
})
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
	@NotBlank
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissaoUsuario other = (PermissaoUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
