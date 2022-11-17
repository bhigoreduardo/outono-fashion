package com.outonofashion.api.model.usuario.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class GeneroUsuarioInput {
	
	@NotNull
	private Long id;

}
