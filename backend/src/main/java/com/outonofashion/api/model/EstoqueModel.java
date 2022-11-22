package com.outonofashion.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueModel {
	
	private Integer quantidade;
	private BigDecimal preco;
	private BigDecimal oferta;
	private CorModel cor;
	private TamanhoModel tamanho;
	
}
