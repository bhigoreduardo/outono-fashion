package com.outonofashion.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@Column(nullable = false, length = 120)
	private String nome;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false, columnDefinition = "TEXT")
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

	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_produto_genero"))
	private Genero genero;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_produto_categoria"))
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_produto_tipo"))
	private Tipo tipo;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_produto_marca"))
	private Marca marca;

	// Estoque
	@OneToMany(mappedBy = "produto")
	private List<Estoque> estoques = new ArrayList<>();

	// Imagem
	@OneToMany(mappedBy = "produto")
	private List<Imagem> imagens = new ArrayList<>();

	// Comentario
	@OneToMany(mappedBy = "produto")
	private List<Comentario> comentarios = new ArrayList<>();
	
	public void active() {
		setAtivo(true);
	}
	
	public void inactive() {
		setAtivo(false);
	}

}
