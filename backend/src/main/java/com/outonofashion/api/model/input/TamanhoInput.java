package com.outonofashion.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TamanhoInput {
	
	@NotBlank
	private String descricao;

}
