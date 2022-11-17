package com.outonofashion.api.assembler.foto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.foto.FotoModel;
import com.outonofashion.domain.model.Foto;

@Component
public class FotoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public FotoModel toModel(Foto foto) {
		return modelMapper.map(foto, FotoModel.class);
	}

}
