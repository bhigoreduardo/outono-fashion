package com.outonofashion.api.assembler.genero;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.genero.input.GeneroInput;
import com.outonofashion.domain.model.Genero;

@Component
public class GeneroModelDiassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Genero toDomain(GeneroInput generoInput) {
		return modelMapper.map(generoInput, Genero.class);
	}
	
	public void copyToDomain(GeneroInput generoInput, Genero genero) {
		modelMapper.map(generoInput, genero);
	}

}
