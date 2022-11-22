package com.outonofashion.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

	private String codigoPedido;
	private BigDecimal subTotal;
	private BigDecimal taxaEntrega;
	private BigDecimal valorTotal;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataPagamento;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEnvio;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataDevolucao;
	private String status;
	private PagamentoResumoModel pagamento;
	private String enderecoApelido;
	private UsuarioResumoModel usuario;
	private List<ItemPedidoModel> itensPedido;
	private String codigoRastreio;

}
