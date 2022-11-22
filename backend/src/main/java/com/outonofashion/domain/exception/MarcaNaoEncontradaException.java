package com.outonofashion.domain.exception;

public class MarcaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 6761252026377505955L;

	public MarcaNaoEncontradaException(String message) {
		super(message);
	}

	public MarcaNaoEncontradaException(Long marcaId) {
		this(String.format("Marca cód. %d não foi encontrada.", marcaId));
	}

}
