package com.outonofashion.domain.model.id;

import java.io.Serializable;

import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.model.Produto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemPedidoId implements Serializable {
	
	private Pedido pedido;
	private Produto produto;
	
}
