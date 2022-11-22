package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.EnderecoModel;
import com.outonofashion.domain.model.Endereco;

@Component
public class EnderecoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EnderecoModel toModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoModel.class);
	}
	
	public List<EnderecoModel> toCollectionModel(List<Endereco> cartoes) {
		return cartoes.stream()
				.map(endereco -> toModel(endereco))
				.collect(Collectors.toList());
	}

}
