package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.TelefoneInput;
import com.outonofashion.domain.model.Telefone;

@Component
public class TelefoneInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Telefone toDomain(TelefoneInput telefoneInput) {
		return modelMapper.map(telefoneInput, Telefone.class);
	}
	
	public void copyToDomain(TelefoneInput telefoneInput, Telefone telefone) {
		modelMapper.map(telefoneInput, telefone);
	}

}
