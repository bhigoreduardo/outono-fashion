package com.outonofashion.domain.exception.pagamento;

import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;

public class PagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PagamentoNaoEncontradoException(String message) {
		super(message);
	}

	public PagamentoNaoEncontradoException(Long pagamentoId) {
		this(String.format("Pagamento cód. %d não foi encontrado.", pagamentoId));
	}

}
