package com.outonofashion.api.model.pedido.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPedidoInput {
	
	@NotNull
	private Long id;

}
