package com.outonofashion.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoIdInput {
	
	@NotNull
	private Long id;

}
