package com.outonofashion.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResumoModel {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String cpfCnpj;

}
