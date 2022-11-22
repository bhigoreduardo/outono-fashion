package com.outonofashion.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoInput {
	
	@NotNull
	@NotBlank
	private String numero;
	
	@NotNull
	@NotBlank
	private String nomeImpresso;
	
	@NotNull
	@NotBlank
	private String mesValidade;
	
	@NotNull
	@NotBlank
	private String anoValidade;
	
	@NotNull
	@NotBlank
	private String cvv;
	
	@Valid
	@NotNull
	private UsuarioIdInput usuario;

}
