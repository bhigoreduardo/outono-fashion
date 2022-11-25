package com.outonofashion.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ItemPedidoInput {
	
	@Valid
	@NotNull
	private ProdutoIdInput produto;
	
	@Valid
	@NotNull
	private TamanhoIdInput tamanho;
	
	@Valid
	@NotNull
	private CorIdInput cor;
	
	@NotNull
	@Positive
	private Integer quantidade;

}
