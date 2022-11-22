package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.CorInput;
import com.outonofashion.domain.model.Cor;

@Component
public class CorInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cor toDomain(CorInput corInput) {
		return modelMapper.map(corInput, Cor.class);
	}

	public void copyToDomain(CorInput corInput, Cor cor) {
		modelMapper.map(corInput, cor);
	}

}
