package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.MarcaModel;
import com.outonofashion.domain.model.Marca;

@Component
public class MarcaModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public MarcaModel toModel(Marca marca) {
		return modelMapper.map(marca, MarcaModel.class);
	}
	
	public List<MarcaModel> toCollectionModel(List<Marca> marcas) {
		return marcas.stream()
				.map(marca -> toModel(marca))
				.collect(Collectors.toList());
	}

}
