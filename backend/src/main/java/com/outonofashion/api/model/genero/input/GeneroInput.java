package com.outonofashion.api.model.genero.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class GeneroInput {

	@NotBlank
	private String descricao;

}
