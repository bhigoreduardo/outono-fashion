package com.outonofashion.domain.exception.foto;

import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoNaoEncontradaException(String message) {
		super(message);
	}

	public FotoNaoEncontradaException(Long fotoId) {
		this(String.format("Foto cód. %d não foi encontrada.", fotoId));
	}

}
