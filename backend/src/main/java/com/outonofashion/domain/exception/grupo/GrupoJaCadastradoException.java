package com.outonofashion.domain.exception.grupo;

import com.outonofashion.domain.exception.EntidadeJaCadastradaException;

public class GrupoJaCadastradoException extends EntidadeJaCadastradaException {

	private static final long serialVersionUID = 1L;

	public GrupoJaCadastradoException(String grupoDescricao) {
		super(String.format("Grupo %s já foi cadastrado.", grupoDescricao));
	}

}
