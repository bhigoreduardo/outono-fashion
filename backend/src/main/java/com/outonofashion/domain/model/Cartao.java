package com.outonofashion.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Cartao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 16)
	private String numero;

	@Column(nullable = false, length = 120)
	private String nomeImpresso;

	@Column(nullable = false, length = 2)
	private String mesValidade;

	@Column(nullable = false, length = 2)
	private String anoValidade;

	@Column(nullable = false, length = 4)
	private String cvv;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_cartao_usuario"))
	private Usuario usuario;

}
