package com.outonofashion.domain.exception;

public class TelefoneNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 2391676135507529947L;

	public TelefoneNaoEncontradoException(String numero) {
		super(String.format("Número %s não foi encontrado.", numero));
	}

}
