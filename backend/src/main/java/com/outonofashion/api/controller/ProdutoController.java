package com.outonofashion.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> findProdutos(
			@RequestParam(defaultValue = "") String[] categoria,
			@RequestParam(defaultValue = "") String[] tipo,
			@RequestParam(defaultValue = "") String[] genero,
			@RequestParam(defaultValue = "") String[] tamanho,
			@RequestParam(defaultValue = "") String[] marca,
			@RequestParam(defaultValue = "") String[] cor,
			@RequestParam(defaultValue = "") String precoMin,
			@RequestParam(defaultValue = "") String precoMax) {

		return produtoService.findProdutos(categoria, tipo, genero, tamanho, marca, cor, precoMin, precoMax);
	}
	
	@GetMapping("{detalhe}")
	public List<Produto> findProdutosLikeDetalhe(@PathVariable String detalhe) {
		return produtoService.findLikeDetalhe(detalhe);
	}
	
	@GetMapping("{nome}/{produtoId}")
	public Produto findProdutoByNomeAndId(@PathVariable String nome, @PathVariable Long produtoId) {
		return produtoService.findByNomeAndId(nome, produtoId);
	}

}
