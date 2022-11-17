package com.outonofashion.api.model.usuario.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoUsuarioInput {
	
	@NotNull
	private Long id;

}
