package com.outonofashion.domain.exception;

public class EstoqueNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 6828793897662172761L;

	public EstoqueNaoEncontradoException(String message) {
		super(message);
	}

	public EstoqueNaoEncontradoException(Long produtoId, Long corId, Long tamanhoId) {
		this(String.format("Estoque produto cód. %d, cor cód. %d e tamanho cód. % não foi encontrado.", produtoId,
				corId, tamanhoId));
	}

}
