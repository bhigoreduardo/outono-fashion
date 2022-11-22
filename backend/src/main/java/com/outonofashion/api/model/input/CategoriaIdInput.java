package com.outonofashion.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaIdInput {
	
	@NotNull
	private Long id;

}
