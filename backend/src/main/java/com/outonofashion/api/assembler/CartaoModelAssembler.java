package com.outonofashion.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.CartaoModel;
import com.outonofashion.domain.model.Cartao;

@Component
public class CartaoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CartaoModel toModel(Cartao cartao) {
		return modelMapper.map(cartao, CartaoModel.class);
	}
	
	public List<CartaoModel> toCollectionModel(List<Cartao> cartoes) {
		return cartoes.stream()
				.map(cartao -> toModel(cartao))
				.collect(Collectors.toList());
	}

}
