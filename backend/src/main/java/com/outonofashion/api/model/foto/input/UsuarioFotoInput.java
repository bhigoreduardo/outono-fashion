package com.outonofashion.api.model.foto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFotoInput {
	
	@NotNull
	private Long id;

}
