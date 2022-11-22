package com.outonofashion.domain.exception;

public class TipoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -7495402367954293109L;

	public TipoNaoEncontradoException(String message) {
		super(message);
	}

	public TipoNaoEncontradoException(Long tipoId) {
		this(String.format("Tipo cód. %d não foi encontrado.", tipoId));
	}

}
