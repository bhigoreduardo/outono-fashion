package com.outonofashion.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResumoModel {

	private Long id;
	private String nome;
	private GeneroResumoModel genero;
	private CategoriaResumoModel categoria;
	private TipoResumoModel tipo;

}
