package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.EstoqueInput;
import com.outonofashion.domain.model.Estoque;

@Component
public class EstoqueInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Estoque toDomain(EstoqueInput estoqueInput) {
		return modelMapper.map(estoqueInput, Estoque.class);
	}

	public void copyToDomain(EstoqueInput estoqueInput, Estoque estoque) {
		modelMapper.map(estoqueInput, estoque);
	}

}
