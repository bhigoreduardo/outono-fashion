package com.outonofashion.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoModel {
	
	private String descricao;
	private Boolean ativo;
	private OffsetDateTime dataAtualizacao;

}
