package com.outonofashion.domain.repository;

import java.util.List;

import com.outonofashion.domain.model.Produto;

public interface ProdutoRepositoryQueries {
	
	public List<Produto> findProdutos(String[] categoria, String[] tipo, String[] genero, String[] tamanho,
			String[] marca, String[] cor, String precoMin, String precoMax, String order);

	public List<Produto> findLikeDetalhe(String detalhe);

}
