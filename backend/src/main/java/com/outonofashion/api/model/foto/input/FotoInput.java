package com.outonofashion.api.model.foto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String tipo;
	
	@Valid
	@NotNull
	private UsuarioFotoInput usuario;

}
