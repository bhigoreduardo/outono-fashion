package com.outonofashion.api.model.usuario;

import java.sql.Date;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpfCnpj;
	private String rgIe;
	private Date dataNascimento;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime dataAtualizacao;
	private GeneroUsuarioModel genero;

}
