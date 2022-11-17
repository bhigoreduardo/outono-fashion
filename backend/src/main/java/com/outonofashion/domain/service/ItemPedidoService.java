package com.outonofashion.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.model.ItemPedido;
import com.outonofashion.domain.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public ItemPedido save(ItemPedido itemPedido) {
		
		return itemPedidoRepository.save(itemPedido);
		
	}

}
