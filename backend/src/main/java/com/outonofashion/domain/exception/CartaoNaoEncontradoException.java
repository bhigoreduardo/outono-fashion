package com.outonofashion.domain.exception;

public class CartaoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 2107054426971596338L;

	public CartaoNaoEncontradoException(String message) {
		super(message);
	}
	
	public CartaoNaoEncontradoException(Long cartaoId) {
		this(String.format("Cartão cód. %s não foi encontrado.", cartaoId));
	}

}
