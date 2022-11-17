package com.outonofashion.domain.model;

import java.math.BigDecimal;

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

@Data
@Entity
//@IdClass(ItemPedidoId.class)
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private BigDecimal precoUnitario;

	@Column(length = 120)
	private String observacao;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_pedido"))
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_produto"))
	private Produto produto;

}
