package com.outonofashion.api.model.input;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@NotBlank
	private String nome;
	
	private String sobrenome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String cpfCnpj;
	
	@NotBlank
	private String rgIe;

	private LocalDate dataNascimento;
	
	@Valid
	@NotNull
	private GeneroIdInput genero;

}
