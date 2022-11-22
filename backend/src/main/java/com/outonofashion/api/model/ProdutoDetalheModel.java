package com.outonofashion.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDetalheModel extends ProdutoModel {
	
	private String descricao;
	private String detalhe;
	private Double largura;
	private Double altura;
	private Double comprimento;
	private Double peso;
	private List<ImagemModel> imagens = new ArrayList<>();
	private List<ComentarioModel> comentarios = new ArrayList<>();

}
