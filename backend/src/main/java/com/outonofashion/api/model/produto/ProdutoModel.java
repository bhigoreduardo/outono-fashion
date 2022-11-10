package com.outonofashion.api.model.produto;

import java.util.ArrayList;
import java.util.List;

import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.model.Estoque;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.model.Tipo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
	
	private Long id;
	private String nome;
	private Genero genero;
	private Categoria categoria;
	private Tipo tipo;
	private Marca marca;
	private List<Estoque> estoques = new ArrayList<>();
	
	/*
	 * id: number;
    nome: string;
    genero: IGenero;
    categoria: ICategoria;
    tipo: ITipo;
    estoques: IEstoque[];
    marca: IMarca
	 */

}
