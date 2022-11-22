package com.outonofashion.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.PedidoInputDisassembler;
import com.outonofashion.api.assembler.PedidoModelAssembler;
import com.outonofashion.api.model.PedidoModel;
import com.outonofashion.api.model.input.PedidoInput;
import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@GetMapping("/{codigoPedido}")
	public PedidoModel findByCodigo(@PathVariable String codigoPedido) {
		Pedido pedido = pedidoService.findByCodigoPedido(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PedidoModel insert(@RequestBody @Valid PedidoInput pedidoInput) {
		Pedido pedido = pedidoInputDisassembler.toDomain(pedidoInput);
		return pedidoModelAssembler.toModel(pedidoService.save(pedido));
	}

}
