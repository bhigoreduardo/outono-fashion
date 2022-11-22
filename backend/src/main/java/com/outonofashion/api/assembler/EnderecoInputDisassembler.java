package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.EnderecoInput;
import com.outonofashion.domain.model.Endereco;

@Component
public class EnderecoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Endereco toDomain(EnderecoInput enderecoInput) {
		return modelMapper.map(enderecoInput, Endereco.class);
	}
	
	public void copyToDomain(EnderecoInput enderecoInput, Endereco endereco) {
		modelMapper.map(enderecoInput, endereco);
	}

}
