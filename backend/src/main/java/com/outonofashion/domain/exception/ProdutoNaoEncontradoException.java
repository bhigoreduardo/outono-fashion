package com.outonofashion.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -4132976415836219864L;

	public ProdutoNaoEncontradoException(String message) {
		super(message);
	}

	public ProdutoNaoEncontradoException(Long produtoId) {
		this(String.format("Produto cód. %d não foi encontrado.", produtoId));
	}

	public ProdutoNaoEncontradoException(String nome, Long produtoId) {
		super(String.format("Produto %s cód. %d não foi encontrado.", nome, produtoId));
	}

}
