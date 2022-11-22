package com.outonofashion.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.EstoqueNaoEncontradoException;
import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Estoque;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tamanho;
import com.outonofashion.domain.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CorService corService;
	
	@Autowired
	private TamanhoService tamanhoService;
	
	public Estoque findByProdutoAndCorAndTamanho(Long produtoId, Long corId, Long tamanhoId) {
		Produto produto = produtoService.findById(produtoId);
		Cor cor = corService.findById(corId);
		Tamanho tamanho = tamanhoService.findById(tamanhoId);
		
		return estoqueRepository.findByProdutoAndCorAndTamanho(produto, cor, tamanho)
				.orElseThrow(() -> new EstoqueNaoEncontradoException(produtoId, corId, tamanhoId));
	}
	
	@Transactional
	public Estoque save(Estoque estoque) {
		Long produtoId = estoque.getProduto().getId();
		Long corId = estoque.getCor().getId();
		Long tamanhoId = estoque.getTamanho().getId();
		
		Produto produto = produtoService.findById(produtoId);
		Cor cor = corService.findById(corId);
		Tamanho tamanho = tamanhoService.findById(tamanhoId);
		
		estoque.setProduto(produto);
		estoque.setCor(cor);
		estoque.setTamanho(tamanho);
		
		return estoqueRepository.save(estoque);
	}

}
