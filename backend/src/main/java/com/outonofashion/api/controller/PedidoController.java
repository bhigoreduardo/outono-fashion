package com.outonofashion.api.controller;

import java.math.BigDecimal;
import java.util.List;

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

import com.outonofashion.api.assembler.pedido.PedidoModelAssembler;
import com.outonofashion.api.assembler.pedido.PedidoModelDiassembler;
import com.outonofashion.api.model.pedido.PedidoModel;
import com.outonofashion.api.model.pedido.input.PedidoInput;
import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoModelDiassembler pedidoModelDiassembler;

	@GetMapping
	public List<PedidoModel> findAll() {
		return pedidoModelAssembler.toCollectionModel(pedidoService.findAll());
	}

	@GetMapping("/{pedidoId}")
	public PedidoModel findById(@PathVariable Long pedidoId) {
		Pedido pedidoCurrent = pedidoService.findById(pedidoId);

		return pedidoModelAssembler.toModel(pedidoCurrent);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PedidoModel insert(@RequestBody @Valid PedidoInput pedidoInput) {

		Pedido pedido = pedidoModelDiassembler.toDomain(pedidoInput);

		BigDecimal subTotal = pedidoInput.getItensPedido().stream()
				.map(itemPedido -> itemPedido.getPrecoUnitario().multiply(new BigDecimal(itemPedido.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal valorTotal = subTotal.add(pedidoInput.getTaxaEntrega());
		
		pedido.setUsuario(new Usuario());
		pedido.getUsuario().setId(pedidoInput.getUsuario().getId());
		
		pedido.setSubTotal(subTotal);
		pedido.setValorTotal(valorTotal);
		
		return pedidoModelAssembler.toModel(pedidoService.save(pedido));
		
	}

}
