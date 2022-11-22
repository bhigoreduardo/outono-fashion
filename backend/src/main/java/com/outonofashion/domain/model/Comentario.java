package com.outonofashion.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
public class Comentario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer classificacao;
	
	@Column(length = 500)
	private String descricao;
	
	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataComentario;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comentario_produto"))
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comentario_usuario"))
	private Usuario usuario;

}
