package com.outonofashion.domain.exception.genero;

import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;

public class GeneroNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public GeneroNaoEncontradoException(String message) {
		super(message);
	}

	public GeneroNaoEncontradoException(Long generoId) {
		this(String.format("Gênero cód. %d não foi encontrado.", generoId));
	}

}
