package com.outonofashion.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private EnderecoIdModel id;
	private String enderecoCep;
	private String enderecoBairro;
	private String enderecoLogradouro;
	private String enderecoNumero;
	private String enderecoComplemento;
	private String enderecoReferencia;
	private String enderecoUf;
	private String enderecoCidade;

}
