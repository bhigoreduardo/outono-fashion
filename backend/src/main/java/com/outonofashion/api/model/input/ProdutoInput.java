package com.outonofashion.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
	
	@NotBlank
	@NotNull
	private String nome;
	
	@NotBlank
	@NotNull
	private String descricao;
	
	@NotBlank
	@NotNull
	private String detalhe;
	
	@NotNull
	@Positive
	private Double largura;
	
	@NotNull
	@Positive
	private Double altura;
	
	@NotNull
	@Positive
	private Double comprimento;
	
	@NotNull
	@Positive
	private Double peso;
	
	@Valid
	@NotNull
	private GeneroIdInput genero;
	
	@Valid
	@NotNull
	private CategoriaIdInput categoria;
	
	@Valid
	@NotNull
	private TipoIdInput tipo;
	
	@Valid
	@NotNull
	private MarcaIdInput marca;

}
