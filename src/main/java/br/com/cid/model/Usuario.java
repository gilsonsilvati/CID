package br.com.cid.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.com.cid.sha2.TransformaStringSHA2;

@Entity
@Table(name = "usuario", indexes = {@Index(columnList = "cpf", unique = true)})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 100, nullable = false)
	private String nome;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	@Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message = "é inválido!")
	private String email;
	
	@NotBlank
	@Column(length = 64, nullable = false)
	private String senha;
	
	@NotBlank
	@CPF
	@Column(length = 14, nullable = false)
	private String cpf;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<PermissaoUsuario> permissoes;

	/* Para fazer auditoria... */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", updatable = false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_modificacao", updatable = true)
	private Date dataModificacao;
	
	@Lob
	@Column(name = "foto_perfil")
	private byte[] fotoPerfil;
	
	// TODO: Adicionar version...
	
	public Usuario() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim().toLowerCase();
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = TransformaStringSHA2.sha2(senha.trim());
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
	}
	
	public List<PermissaoUsuario> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<PermissaoUsuario> permissoes) {
		this.permissoes = permissoes;
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
	
	public byte[] getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(byte[] fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
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
		Usuario other = (Usuario) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}