package com.outonofashion.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.outonofashion.domain.model.TipoCupom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupomModel {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal oferta;
	private TipoCupom tipoCupom;
	private Boolean ativo;
	private OffsetDateTime dataOferta;
	private OffsetDateTime dataEncerramento;

}
