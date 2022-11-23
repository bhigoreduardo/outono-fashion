package com.outonofashion.api.model;

import java.time.LocalDate;
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
	private String cpfCnpj;
	private String rgIe;
	private LocalDate dataNascimento;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime dataAtualizacao;
	private GeneroModel genero;
	private Boolean newsletter;

}
