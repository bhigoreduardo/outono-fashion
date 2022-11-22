package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.CategoriaInput;
import com.outonofashion.domain.model.Categoria;

@Component
public class CategoriaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Categoria toDomain(CategoriaInput categoriaInput) {
		return modelMapper.map(categoriaInput, Categoria.class);
	}

	public void copyToDomain(CategoriaInput categoriaInput, Categoria categoria) {
		modelMapper.map(categoriaInput, categoria);
	}

}
