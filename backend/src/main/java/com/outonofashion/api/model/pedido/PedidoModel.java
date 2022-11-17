package com.outonofashion.api.model.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.outonofashion.domain.model.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

	private Long id;
	private BigDecimal taxaEntrega;
	private BigDecimal subTotal;
	private BigDecimal valorTotal;
	private PagamentoPedidoModel pagamento;
	private OffsetDateTime dataPedido;
	private String rastreio;
	private Status status;

}
