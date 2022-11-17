package com.outonofashion.domain.exception.usuario;

import com.outonofashion.domain.exception.EntidadeJaCadastradaException;

public class UsuarioJaCadastradoException extends EntidadeJaCadastradaException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioJaCadastradoException(String message) {
		super(message);
	}

}
