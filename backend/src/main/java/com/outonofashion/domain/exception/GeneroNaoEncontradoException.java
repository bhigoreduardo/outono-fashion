package com.outonofashion.domain.exception;

public class GeneroNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public GeneroNaoEncontradoException(String message) {
		super(message);
	}

	public GeneroNaoEncontradoException(Long generoId) {
		this(String.format("Gênero cód. %d não foi encontrado.", generoId));
	}

}
