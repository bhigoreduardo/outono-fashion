package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.TamanhoModel;
import com.outonofashion.domain.model.Tamanho;

@Component
public class TamanhoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TamanhoModel toModel(Tamanho tamanho) {
		return modelMapper.map(tamanho, TamanhoModel.class);
	}
	
	public List<TamanhoModel> toCollectionModel(List<Tamanho> tamanhos) {
		return tamanhos.stream()
				.map(tamanho -> toModel(tamanho))
				.collect(Collectors.toList());
	}

}
