package com.outonofashion.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneInput {
	
	@NotBlank
	private String numero;
	
	@Valid
	@NotNull
	private UsuarioIdInput usuario;

}
