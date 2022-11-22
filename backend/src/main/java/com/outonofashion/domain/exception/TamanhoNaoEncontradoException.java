package com.outonofashion.domain.exception;

public class TamanhoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9041738876129466856L;

	public TamanhoNaoEncontradoException(String message) {
		super(message);
	}

	public TamanhoNaoEncontradoException(Long tamanhoId) {
		this(String.format("Tamanho cód. %d não foi encontrado.", tamanhoId));
	}

}
