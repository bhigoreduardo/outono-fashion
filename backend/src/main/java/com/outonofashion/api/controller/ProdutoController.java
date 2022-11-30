package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.MarcaModelAssembler;
import com.outonofashion.api.assembler.ProdutoInputDisassembler;
import com.outonofashion.api.assembler.ProdutoModelAssembler;
import com.outonofashion.api.assembler.TipoModelAssembler;
import com.outonofashion.api.model.MarcaModel;
import com.outonofashion.api.model.ProdutoDetalheModel;
import com.outonofashion.api.model.ProdutoModel;
import com.outonofashion.api.model.TipoModel;
import com.outonofashion.api.model.input.ProdutoInput;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.repository.MarcaRepository;
import com.outonofashion.domain.repository.ProdutoRepository;
import com.outonofashion.domain.repository.TipoRepository;
import com.outonofashion.domain.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@Autowired
	private TipoRepository tipoRepository;

	@Autowired
	private TipoModelAssembler tipoModelAssembler;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private MarcaModelAssembler marcaModelAssembler;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ProdutoModel insert(@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produto = produtoInputDisassembler.toDomain(produtoInput);

		return produtoModelAssembler.toModel(produtoService.save(produto));
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel update(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoCurrent = produtoService.findById(produtoId);

		produtoInputDisassembler.copyToDomain(produtoInput, produtoCurrent);

		return produtoModelAssembler.toModel(produtoService.save(produtoCurrent));
	}

	@PutMapping("/{produtoId}/ativo")
	public ResponseEntity<Void> active(@PathVariable Long produtoId) {
		produtoService.active(produtoId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{produtoId}/inativo")
	public ResponseEntity<Void> inactive(@PathVariable Long produtoId) {
		produtoService.inactive(produtoId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/ativacoes")
	public ResponseEntity<Void> activeMultiples(@RequestBody List<Long> produtosId) {
		produtoService.active(produtosId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/inativacoes")
	public ResponseEntity<Void> inactiveMultiples(@RequestBody List<Long> produtosId) {
		produtoService.inactive(produtosId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public List<ProdutoModel> findProdutos(@RequestParam(defaultValue = "") String[] categoria,
			@RequestParam(defaultValue = "") String[] tipo, @RequestParam(defaultValue = "") String[] genero,
			@RequestParam(defaultValue = "") String[] tamanho, @RequestParam(defaultValue = "") String[] marca,
			@RequestParam(defaultValue = "") String[] cor, @RequestParam(defaultValue = "") String precoMin,
			@RequestParam(defaultValue = "") String precoMax, @RequestParam(defaultValue = "") String order) {

		return produtoModelAssembler.toCollectionModel(produtoRepository.findProdutos(categoria, tipo, genero, tamanho,
				marca, cor, precoMin, precoMax, order));

	}

	@GetMapping("/{detalhe}")
	public List<ProdutoModel> findProdutosLikeDetalhe(@PathVariable String detalhe) {
		return produtoModelAssembler.toCollectionModel(produtoRepository.findLikeDetalhe(detalhe));
	}

	@GetMapping("/{nome}/{produtoId}")
	public ProdutoDetalheModel findProdutoByNomeAndId(@PathVariable String nome, @PathVariable Long produtoId) {
		return produtoModelAssembler.toDetalheModel(produtoService.findByNomeAndId(nome, produtoId));
	}

	@GetMapping("/tipos")
	public List<TipoModel> findByGeneroAndCategoria(@RequestParam String genero, @RequestParam String categoria) {
		return tipoModelAssembler.toCollectionModel(tipoRepository.findByGeneroAndCategoria(genero, categoria));
	}

	@GetMapping("/marcas")
	public List<MarcaModel> findByGenero(@RequestParam String genero) {
		return marcaModelAssembler.toCollectionModel(marcaRepository.findByGenero(genero));
	}

}
