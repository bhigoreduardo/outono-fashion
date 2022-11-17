package com.outonofashion.api.assembler.foto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.foto.input.FotoInput;
import com.outonofashion.domain.model.Foto;

@Component
public class FotoModelDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Foto toDomain(FotoInput fotoInput) {
		return modelMapper.map(fotoInput, Foto.class);
	}
	
	public void copyToDomain(FotoInput fotoInput, Foto foto) {
		modelMapper.map(fotoInput, foto);
	}

}
