package com.outonofashion.api.model.produto;

import java.util.ArrayList;
import java.util.List;

import com.outonofashion.domain.model.Comentario;
import com.outonofashion.domain.model.Imagem;

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
	private List<Imagem> imagens = new ArrayList<>();
	private List<Comentario> comentarios = new ArrayList<>();

}
