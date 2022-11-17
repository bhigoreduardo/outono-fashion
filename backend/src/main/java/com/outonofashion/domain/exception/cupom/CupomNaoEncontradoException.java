package com.outonofashion.domain.exception.cupom;

import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;

public class CupomNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CupomNaoEncontradoException(String message) {
		super(message);
	}
	
	public CupomNaoEncontradoException(Long cupomId) {
		this(String.format("Cupom cód. %d não foi encontrado.", cupomId));
	}

}
