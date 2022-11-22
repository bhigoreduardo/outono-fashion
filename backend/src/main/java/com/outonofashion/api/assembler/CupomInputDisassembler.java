package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.CupomInput;
import com.outonofashion.domain.model.Cupom;

@Component
public class CupomInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cupom toDomain(CupomInput cupomInput) {
		return modelMapper.map(cupomInput, Cupom.class);
	}

	public void copyToDomain(CupomInput cupomInput, Cupom cupom) {
		modelMapper.map(cupomInput, cupom);
	}

}
