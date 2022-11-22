package com.outonofashion.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoIdInput {
	
	@NotNull
	private Long id;

}
