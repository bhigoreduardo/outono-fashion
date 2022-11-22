package com.outonofashion.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String message) {
		super(message);
	}

	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Permissão cód. %d não foi encontrada.", permissaoId));
	}

}
