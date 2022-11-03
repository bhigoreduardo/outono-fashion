package com.outonofashion.domain.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Produto;

@Repository
public interface IProdutoService {

	public List<Produto> findProdutos(String[] categoria, String[] tipo, String[] genero, String[] tamanho,
			String[] marca, String[] cor, String precoMin, String precoMax);

	public List<Produto> findLikeDetalhe(String detalhe);

}