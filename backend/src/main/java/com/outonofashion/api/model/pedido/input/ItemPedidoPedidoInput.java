package com.outonofashion.api.model.pedido.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ItemPedidoPedidoInput {
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@Positive
	@NotNull
	private BigDecimal precoUnitario;
	
	private String observacao;
	
	@Valid
	@NotNull
	private ProdutoPedidoInput produto;

}
