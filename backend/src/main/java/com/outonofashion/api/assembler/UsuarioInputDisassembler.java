package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.UsuarioInput;
import com.outonofashion.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomain(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public void copyToDomain(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}

}
