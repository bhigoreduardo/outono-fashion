package com.outonofashion.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

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

	private String codigoPedido;

	@Column(nullable = false)
	private BigDecimal subTotal;

	@Column(nullable = false)
	private BigDecimal taxaEntrega;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataPedido;

	private OffsetDateTime dataPagamento;

	private OffsetDateTime dataCancelamento;

	private OffsetDateTime dataEnvio;

	private OffsetDateTime dataEntrega;

	private OffsetDateTime dataDevolucao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status = Status.AGUARDANDO_PAGAMENTO;

	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_pagamento"))
	@ManyToOne
	private Pagamento pagamento;
	
	@Column(nullable = false)
	private String enderecoApelido;

	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_usuario"))
	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();

	@ManyToOne
	private Cupom cupom;

	@Column(length = 60)
	private String codigoRastreio;

	public void calculateValorTotal() {
		getItensPedido().forEach(ItemPedido::calculatePrecoTotal);

		this.subTotal = getItensPedido().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		if (getCupom() != null) {
			if (getCupom().getTipoCupom() == TipoCupom.PORCENTAGEM) {
				BigDecimal desconto = getCupom().getOferta().divide(new BigDecimal("100")).multiply(subTotal);
				
				this.subTotal = this.subTotal.subtract(desconto);
			} else if (getCupom().getTipoCupom() == TipoCupom.FIXO) {
				this.subTotal = this.subTotal.subtract(getCupom().getOferta());
			}
		}
		
		this.valorTotal = this.subTotal.add(this.taxaEntrega);
	}

	@PrePersist
	private void generateCodigo() {
		setCodigoPedido(UUID.randomUUID().toString());
	}

}
