package com.outonofashion.api.model.grupo.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoGrupoInput {
	
	@NotNull
	private Long id;

}
