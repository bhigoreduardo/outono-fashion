package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.TelefoneModel;
import com.outonofashion.domain.model.Telefone;

@Component
public class TelefoneModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TelefoneModel toModel(Telefone telefone) {
		return modelMapper.map(telefone, TelefoneModel.class);
	}
	
	public List<TelefoneModel> toCollectionModel(List<Telefone> cartoes) {
		return cartoes.stream()
				.map(telefone -> toModel(telefone))
				.collect(Collectors.toList());
	}

}
