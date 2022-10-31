package com.outonofashion.domain.model;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(nullable = false, length = 20)
	private String senha;
	
	@Column(nullable = false, length = 14, unique = true)
	private String cpfCnpj;
	
	@Column(nullable = false, length = 14)
	private String rgIe;
	
	private Date dataNascimento;
	
	@Column(nullable = false)
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@Column(nullable = false)
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@Column(length = 30)
	private String bancoConta;
	
	@Column(length = 30)
	private String agenciaBancaria;
	
	@Column(length = 30)
	private String contaBancaria;
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private Genero genero;
	
	@ManyToOne
	private Foto foto;
	
	// Endereco
	
	// Cartao
	
	@ManyToOne
	private Grupo grupo;
	
	// Telefone
	
}
