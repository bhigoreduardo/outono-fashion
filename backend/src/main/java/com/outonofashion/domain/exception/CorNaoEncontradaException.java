package com.outonofashion.domain.exception;

public class CorNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 7104220916480025587L;

	public CorNaoEncontradaException(String message) {
		super(message);
	}

	public CorNaoEncontradaException(Long corId) {
		this(String.format("Cor cód. %d não foi encontrada.", corId));
	}

}
