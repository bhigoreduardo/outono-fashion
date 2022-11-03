package com.outonofashion.domain.model.id;

import java.io.Serializable;

import com.outonofashion.domain.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EnderecoId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String enderecoApelido;
	private Usuario usuario;
	
}
