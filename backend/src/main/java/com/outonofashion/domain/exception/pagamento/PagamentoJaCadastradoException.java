package com.outonofashion.domain.exception.pagamento;

import com.outonofashion.domain.exception.EntidadeJaCadastradaException;

public class PagamentoJaCadastradoException extends EntidadeJaCadastradaException {

	private static final long serialVersionUID = 1L;

	public PagamentoJaCadastradoException(String pagamentoDescricao) {
		super(String.format("Pagamento %s já foi cadastrado.", pagamentoDescricao));
	}

}
