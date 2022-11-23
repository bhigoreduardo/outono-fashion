package com.outonofashion.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@Valid
	@NotNull
	private EnderecoIdInput id;
	
	@Valid
	@NotNull
	private UsuarioIdInput usuario;
	
	@NotBlank
	private String enderecoCep;
	
	@NotBlank
	private String enderecoBairro;
	
	@NotBlank
	private String enderecoLogradouro;
	
	@NotBlank
	private String enderecoNumero;
	
	private String enderecoComplemento;
	private String enderecoReferencia;
	
	@NotBlank
	private String enderecoUf;
	
	@NotBlank
	private String enderecoCidade;
	
	private Boolean enderecoAtivo;

}
