package com.outonofashion.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaInput extends UsuarioInput {
	
	@NotBlank
	private String senha;

}
