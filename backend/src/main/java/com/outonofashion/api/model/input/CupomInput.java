package com.outonofashion.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupomInput {
	
	@NotBlank
	private String nome;
	
	private String descricao;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal oferta;
	
	//@NotNull
	private OffsetDateTime dataEncerramento = OffsetDateTime.now();

}
