package com.outonofashion.api.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginModel {
	
	private String nome;
	private String sobrenome;
	private String email;
	private String cpfCnpj;
	private GeneroResumoModel genero;
	private LocalDate dataNascimento;
	private Boolean newsletter;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime dataAtualizacao;

}
