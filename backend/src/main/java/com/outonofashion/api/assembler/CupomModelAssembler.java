package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.CupomModel;
import com.outonofashion.domain.model.Cupom;

@Component
public class CupomModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CupomModel toModel(Cupom cupom) {
		return modelMapper.map(cupom, CupomModel.class);
	}
	
	public List<CupomModel> toCollectionModel(List<Cupom> cupons) {
		return cupons.stream()
				.map(cupom -> toModel(cupom))
				.collect(Collectors.toList());
	}

}
