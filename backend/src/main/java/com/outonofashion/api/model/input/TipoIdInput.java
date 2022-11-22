package com.outonofashion.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoIdInput {
	
	@NotNull
	private Long id;

}
