package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.MarcaInput;
import com.outonofashion.domain.model.Marca;

@Component
public class MarcaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Marca toDomain(MarcaInput marcaInput) {
		return modelMapper.map(marcaInput, Marca.class);
	}

	public void copyToDomain(MarcaInput marcaInput, Marca marca) {
		modelMapper.map(marcaInput, marca);
	}

}
