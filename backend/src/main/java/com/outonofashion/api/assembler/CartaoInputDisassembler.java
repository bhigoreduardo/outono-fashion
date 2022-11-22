package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.CartaoInput;
import com.outonofashion.domain.model.Cartao;

@Component
public class CartaoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cartao toDomain(CartaoInput cartaoInput) {
		return modelMapper.map(cartaoInput, Cartao.class);
	}
	
	public void copyToDomain(CartaoInput cartaoInput, Cartao cartao) {
		modelMapper.map(cartaoInput, cartao);
	}

}
