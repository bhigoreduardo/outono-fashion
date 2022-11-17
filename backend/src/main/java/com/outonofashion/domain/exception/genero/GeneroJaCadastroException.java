package com.outonofashion.domain.exception.genero;

import com.outonofashion.domain.exception.EntidadeJaCadastradaException;

public class GeneroJaCadastroException extends EntidadeJaCadastradaException {

	private static final long serialVersionUID = 1L;

	public GeneroJaCadastroException(String generoDescricao) {
		super(String.format("Gênero %s já foi cadastrado.", generoDescricao));
	}

}
