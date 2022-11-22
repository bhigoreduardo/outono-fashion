package com.outonofashion.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {
	
	private ProdutoResumoModel produto;
	private TamanhoModel tamanho;
	private CorResumoModel cor;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
}
