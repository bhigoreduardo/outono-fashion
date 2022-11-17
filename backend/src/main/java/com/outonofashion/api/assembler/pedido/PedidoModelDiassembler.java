package com.outonofashion.api.assembler.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.pedido.input.PedidoInput;
import com.outonofashion.domain.model.Pedido;

@Component
public class PedidoModelDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomain(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}

	public void copyToDomain(PedidoInput pedidoInput, Pedido pedido) {
		modelMapper.map(pedidoInput, pedido);
	}

}
