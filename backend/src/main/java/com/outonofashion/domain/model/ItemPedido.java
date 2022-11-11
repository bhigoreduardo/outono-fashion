package com.outonofashion.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.outonofashion.domain.model.id.ItemPedidoId;

import lombok.Data;

@Data
@Entity
@IdClass(ItemPedidoId.class)
public class ItemPedido {
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@Column(length = 120)
	private String observacao;
	
	@Id
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_pedido"))
	private Pedido pedido;
	
	@Id
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_produto"))
	private Produto produto;
	
}
