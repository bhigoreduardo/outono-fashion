package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.CorModel;
import com.outonofashion.domain.model.Cor;

@Component
public class CorModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CorModel toModel(Cor cor) {
		return modelMapper.map(cor, CorModel.class);
	}
	
	public List<CorModel> toCollectionModel(List<Cor> cores) {
		return cores.stream()
				.map(cor -> toModel(cor))
				.collect(Collectors.toList());
	}

}
