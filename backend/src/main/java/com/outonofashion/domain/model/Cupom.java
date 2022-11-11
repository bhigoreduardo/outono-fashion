package com.outonofashion.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Cupom {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String nome;

	@Column(length = 120)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal oferta;
	
	@Column(nullable = false)
	private TipoCupom tipoCupom = TipoCupom.PROCENTAGEM;

	@Column(nullable = false)
	private Boolean ativo;

	@Column(nullable = false)
	@CreationTimestamp
	private OffsetDateTime dataOferta;

	@Column(nullable = false)
	private OffsetDateTime dataEncerramento;

}
