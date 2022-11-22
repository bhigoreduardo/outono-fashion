package com.outonofashion.domain.exception;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 828246924608288199L;

	public EnderecoNaoEncontradoException(String message) {
		super(message);
	}
	
	public EnderecoNaoEncontradoException(String enderecoApelido, String usuarioNome) {
		this(String.format("Endereco %s não foi encontrado no usuário %s", enderecoApelido, usuarioNome));
	}

}
