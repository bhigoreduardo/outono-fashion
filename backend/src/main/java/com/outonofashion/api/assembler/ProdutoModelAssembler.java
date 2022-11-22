package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.ProdutoDetalheModel;
import com.outonofashion.api.model.ProdutoModel;
import com.outonofashion.domain.model.Produto;

@Component
public class ProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoDetalheModel toDetalheModel(Produto produto) {
		return modelMapper.map(produto, ProdutoDetalheModel.class);
	}

	public ProdutoModel toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);
	}

	public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
		return produtos.stream()
			.map(produto -> toModel(produto))
			.collect(Collectors.toList());
	}

}
