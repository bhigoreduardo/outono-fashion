package com.outonofashion.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String nome;

	@Column(length = 60)
	private String sobrenome;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false, length = 14, unique = true)
	private String cpfCnpj;

	@Column(nullable = false, length = 14)
	private String rgIe;

	private LocalDate dataNascimento;

	@Column(nullable = false)
	@CreationTimestamp
	private OffsetDateTime dataCadastro;

	@Column(nullable = false)
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;

	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;

	// Banco

	@JoinColumn(nullable = false)
	@ManyToOne
	private Genero genero;

	// Foto

	// Endereco
	@OneToMany(mappedBy = "usuario")
	private Set<Endereco> enderecos = new HashSet<>();

	@OneToMany(mappedBy = "usuario")
	private Set<Cartao> cartoes = new HashSet<>();

	@JoinColumn(nullable = false)
	@ManyToMany
	@JoinTable(name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_usuario_id")),
			inverseJoinColumns = @JoinColumn(name = "grupo_id", foreignKey = @ForeignKey(name = "fk_grupo_id")))
	private Set<Grupo> grupos = new HashSet<>();

	// Telefone
	
	public boolean isSenhaCheck(String senha) {
		return getSenha().equals(senha);
	}
	
	public boolean isSenhaNotCheck(String senha) {
		return !isSenhaCheck(senha);
	}
	
	public boolean addGrupo(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	public boolean removeGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	
	public boolean isNovoUsuario() {
		return getId() == null;
	}

}
