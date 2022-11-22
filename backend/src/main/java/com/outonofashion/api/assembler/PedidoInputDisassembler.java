package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.PedidoInput;
import com.outonofashion.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomain(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}

	public void copyToDomain(PedidoInput pedidoInput, Pedido pedido) {
		modelMapper.map(pedidoInput, pedido);
	}

}
