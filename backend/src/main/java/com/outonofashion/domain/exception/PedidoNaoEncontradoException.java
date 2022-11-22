package com.outonofashion.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9064127959521571234L;

	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format("Pedido cód. %s não foi encontrado.", codigoPedido));
	}

}
