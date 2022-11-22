package com.outonofashion.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioModel {

	private Integer classificacao;
	private String descricao;
	private OffsetDateTime dataComentario;
	private UsuarioPublicoModel usuario;
	
}
