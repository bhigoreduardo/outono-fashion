package com.outonofashion.domain.model;

import java.math.BigDecimal;
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
@Data
@Entity
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subTotal;
	
	@Column(nullable = false)
	private BigDecimal taxaEntrega;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@Column(nullable = false)
	@CreationTimestamp
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataPagamento;
	
	private OffsetDateTime dataEnvio;
	
	private OffsetDateTime dataEntrega;
	
	private OffsetDateTime dataDevolucao;
	
	@Column(nullable = false, length = 20)
	private Status status = Status.AGUARDANDO_PAGAMENTO;
	
	@ManyToOne
	private Cupom cupom;
	
	@Column(length = 60)
	private String rastreio;
	
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_pagamento"))
	@ManyToOne
	private Pagamento pagamento;
	
}
