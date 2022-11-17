package com.outonofashion.api.model.permissao.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInput {
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Integer acesso;

}
