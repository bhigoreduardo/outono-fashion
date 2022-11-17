package com.outonofashion.domain.exception.pedido;

import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String message) {
		super(message);
	}

	public PedidoNaoEncontradoException(Long pedidoId) {
		this(String.format("Pedido cód. %d não foi encontrado.", pedidoId));
	}

}
