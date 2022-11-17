package com.outonofashion.domain.model.id;

import java.io.Serializable;

import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemPedidoId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private Produto produto;
	
}
