package com.outonofashion.api.model.grupo.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	@NotBlank
	private String descricao;

	@NotNull
	@Valid
	private PermissaoGrupoInput permissao;

}
