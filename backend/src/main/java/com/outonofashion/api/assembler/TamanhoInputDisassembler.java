package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.TamanhoInput;
import com.outonofashion.domain.model.Tamanho;

@Component
public class TamanhoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Tamanho toDomain(TamanhoInput tamanhoInput) {
		return modelMapper.map(tamanhoInput, Tamanho.class);
	}

	public void copyToDomain(TamanhoInput tamanhoInput, Tamanho tamanho) {
		modelMapper.map(tamanhoInput, tamanho);
	}

}
