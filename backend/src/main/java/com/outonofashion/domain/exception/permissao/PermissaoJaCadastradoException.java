package com.outonofashion.domain.exception.permissao;

import com.outonofashion.domain.exception.EntidadeJaCadastradaException;

public class PermissaoJaCadastradoException extends EntidadeJaCadastradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoJaCadastradoException(String permissaoDescricao) {
		super(String.format("Permissão %s já foi cadastrada.", permissaoDescricao));
	}

}
