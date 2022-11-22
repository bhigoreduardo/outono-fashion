package com.outonofashion.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueInput {
	
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	private BigDecimal oferta;
	
	@Valid
	@NotNull
	private ProdutoIdInput produto;
	
	@Valid
	@NotNull
	private CorIdInput cor;
	
	@Valid
	@NotNull
	private TamanhoIdInput tamanho;

}
