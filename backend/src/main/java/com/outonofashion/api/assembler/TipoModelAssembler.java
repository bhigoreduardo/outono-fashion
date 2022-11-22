package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.TipoModel;
import com.outonofashion.domain.model.Tipo;

@Component
public class TipoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TipoModel toModel(Tipo tipo) {
		return modelMapper.map(tipo, TipoModel.class);
	}
	
	public List<TipoModel> toCollectionModel(List<Tipo> tipos) {
		return tipos.stream()
				.map(tipo -> toModel(tipo))
				.collect(Collectors.toList());
	}

}
