package com.outonofashion.api.model.input;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
	private PagamentoIdInput pagamento;
	
	@NotBlank
	private String enderecoApelido;
	
	@Valid
	@NotNull
	private UsuarioIdInput usuario;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInput> itensPedido;
	
	private CupomIdInput cupom;
	
}
