package com.outonofashion.domain.model.id;

import java.io.Serializable;

import com.outonofashion.domain.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnderecoId implements Serializable {
	
	private String enderecoApelido;
	private Usuario usuario;
	
}
