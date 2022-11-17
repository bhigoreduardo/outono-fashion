package com.outonofashion.api.model.usuario.input;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class UsuarioInput {
	
	@NotBlank
	private String nome;
	
	private String sobrenome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String cpfCnpj;
	
	@NotBlank
	private String rgIe;
	
	private Date dataNascimento;
	
	@Valid
	@NotNull
	private GeneroUsuarioInput genero;
	
	@Valid
	@NotNull
	private GrupoUsuarioInput grupo;

}
