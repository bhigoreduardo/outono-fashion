package com.outonofashion.domain.exception;

public class CupomNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -6707875388197964201L;

	public CupomNaoEncontradoException(String message) {
		super(message);
	}
	
	public CupomNaoEncontradoException(Long cupomId) {
		this(String.format("Cupom cód. %d não foi encontrado.", cupomId));
	}

}
