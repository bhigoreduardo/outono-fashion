package com.outonofashion.domain.exception.grupo;

import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public GrupoNaoEncontradoException(String message) {
		super(message);
	}

	public GrupoNaoEncontradoException(Long grupoId) {
		this(String.format("Grupo cód. %d não foi encontrado.", grupoId));
	}

}
