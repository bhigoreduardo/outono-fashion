package com.outonofashion.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String detalhe;

	@Column(nullable = false)
	private Double largura;

	@Column(nullable = false)
	private Double altura;

	@Column(nullable = false)
	private Double comprimento;

	@Column(nullable = false)
	private Double peso;

	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataAtualizacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Genero genero;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Tipo tipo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Marca marca;

}
