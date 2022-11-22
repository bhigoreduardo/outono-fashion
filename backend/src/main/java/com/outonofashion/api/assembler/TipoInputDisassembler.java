package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.TipoInput;
import com.outonofashion.domain.model.Tipo;

@Component
public class TipoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Tipo toDomain(TipoInput tipoInput) {
		return modelMapper.map(tipoInput, Tipo.class);
	}

	public void copyToDomain(TipoInput tipoInput, Tipo tipo) {
		modelMapper.map(tipoInput, tipo);
	}

}
