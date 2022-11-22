package com.outonofashion.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
	
	private Long id;
	private String nome;
	private GeneroModel genero;
	private CategoriaModel categoria;
	private TipoModel tipo;
	private MarcaModel marca;
	private List<EstoqueModel> estoques = new ArrayList<>();

}
