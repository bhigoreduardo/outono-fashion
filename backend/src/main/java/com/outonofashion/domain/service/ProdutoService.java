package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.ProdutoNaoEncontradoException;
import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tipo;
import com.outonofashion.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private TipoService tipoService;
	
	@Autowired
	private MarcaService marcaService;

	public Produto findById(Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}

	public Produto findByNomeAndId(String nome, Long produtoId) {
		return produtoRepository.findByNomeAndId(nome, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(nome, produtoId));
	}
	
	@Transactional
	public Produto save(Produto produto) {
		Long generoId = produto.getGenero().getId();
		Long categoriaId = produto.getCategoria().getId();
		Long tipoId = produto.getTipo().getId();
		Long marcaId = produto.getMarca().getId();
		
		Genero genero = generoService.findById(generoId);
		Categoria categoria = categoriaService.findById(categoriaId);
		Tipo tipo = tipoService.findById(tipoId);
		Marca marca = marcaService.findById(marcaId);
		
		produto.setGenero(genero);
		produto.setCategoria(categoria);
		produto.setTipo(tipo);
		produto.setMarca(marca);
		
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public void active(Long produtoId) {
		Produto produto = findById(produtoId);
		produto.active();
	}
	
	@Transactional
	public void inactive(Long produtoId) {
		Produto produto = findById(produtoId);
		produto.inactive();
	}
	
	@Transactional
	public void active(List<Long> produtosId) {
		produtosId.forEach(this::active);
	}
	
	@Transactional
	public void inactive(List<Long> produtosId) {
		produtosId.forEach(this::inactive);
	}

}
