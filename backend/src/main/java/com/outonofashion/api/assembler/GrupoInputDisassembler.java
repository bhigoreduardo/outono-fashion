package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.GrupoInput;
import com.outonofashion.domain.model.Grupo;

@Component
public class GrupoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomain(GrupoInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}

	public void copyToDomain(GrupoInput grupoInput, Grupo grupoCurrent) {
		modelMapper.map(grupoInput, grupoCurrent);
	}

}
