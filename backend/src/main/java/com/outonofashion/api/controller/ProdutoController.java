package com.outonofashion.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.produto.ProdutoModelAssembler;
import com.outonofashion.api.model.produto.ProdutoDetalheModel;
import com.outonofashion.api.model.produto.ProdutoModel;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@GetMapping
	public List<ProdutoModel> findProdutos(
			@RequestParam(defaultValue = "") String[] categoria,
			@RequestParam(defaultValue = "") String[] tipo,
			@RequestParam(defaultValue = "") String[] genero,
			@RequestParam(defaultValue = "") String[] tamanho,
			@RequestParam(defaultValue = "") String[] marca,
			@RequestParam(defaultValue = "") String[] cor,
			@RequestParam(defaultValue = "") String precoMin,
			@RequestParam(defaultValue = "") String precoMax,
			@RequestParam(defaultValue = "") String order) {

		return produtoModelAssembler.toCollectionModel(
				produtoService.findProdutos(categoria, tipo, genero, tamanho, marca, cor, precoMin, precoMax, order));
		
	}
	
	@GetMapping("{detalhe}")
	public List<Produto> findProdutosLikeDetalhe(@PathVariable String detalhe) {
		return produtoService.findLikeDetalhe(detalhe);
	}

	@GetMapping("{nome}/{produtoId}")
	public ProdutoDetalheModel findProdutoByNomeAndId(@PathVariable String nome, @PathVariable Long produtoId) {		
		return produtoModelAssembler.toDetalheModel(produtoService.findByNomeAndId(nome, produtoId));
	}

}
