package br.com.cid.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.com.cid.md5.TransformaStringMD5;

@Entity
@Table(name = "usuario")
@NamedQueries({
	@NamedQuery(name = "Usuario.buscarTodos", query = "select u from Usuario u"),
	@NamedQuery(name = "Usuario.buscarPorNome", query = "select u from Usuario u where u.nome = :nome")
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(length = 100, nullable = false)
	private String nome;
	
	@NotEmpty
	@Email
	@Column(length = 50)
	private String email;
	
	@NotEmpty
	@Column(length = 50, nullable = false)
	private String senha;
	
	@NotEmpty
	@CPF
	@Column(length = 14)
	private String cpf;

	@OneToMany(mappedBy = "usuario")
	private List<PermissaoUsuario> permissoes;
	
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
		this.senha = TransformaStringMD5.md5(senha.trim());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}