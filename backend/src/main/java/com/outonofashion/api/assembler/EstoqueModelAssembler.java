package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.EstoqueModel;
import com.outonofashion.domain.model.Estoque;

@Component
public class EstoqueModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstoqueModel toModel(Estoque estoque) {
		return modelMapper.map(estoque, EstoqueModel.class);
	}
	
	public List<EstoqueModel> toCollectionModel(List<Estoque> estoques) {
		return estoques.stream()
				.map(estoque -> toModel(estoque))
				.collect(Collectors.toList());
	}

}
