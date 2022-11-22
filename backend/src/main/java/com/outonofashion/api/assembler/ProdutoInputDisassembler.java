package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.ProdutoInput;
import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tipo;

@Component
public class ProdutoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Produto toDomain(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}

	public void copyToDomain(ProdutoInput produtoInput, Produto produto) {
		produto.setGenero(new Genero());
		produto.setCategoria(new Categoria());
		produto.setTipo(new Tipo());
		produto.setMarca(new Marca());
		
		modelMapper.map(produtoInput, produto);
	}

}
