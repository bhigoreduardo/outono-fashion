package com.outonofashion.domain.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 9163741929098988785L;

	public CategoriaNaoEncontradaException(String message) {
		super(message);
	}

	public CategoriaNaoEncontradaException(Long categoriaId) {
		this(String.format("Categoria cód. %d não foi encontrada.", categoriaId));
	}

}
