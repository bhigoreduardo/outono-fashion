package com.outonofashion.api.model.pedido.input;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaEntrega;
	
	@Valid
	@NotNull
	private PagamentoPedidoInput pagamento;
	
	@Valid
	@NotNull
	private UsuarioPedidoInput usuario;
	
	@Valid
	@NotNull
	private List<ItemPedidoPedidoInput> itensPedido;
	
	private CupomPedidoInput cupom;
	
	/*
	 * @NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private UsuarioPedidoInput usuario;
	
	@Valid
	@NotNull
	private PagamentoPedidoInput pagamento;
	
	@Valid
	@NotNull
	private List<ProdutoPedidoPedidoInput> produtoPedido;
	 */

}
