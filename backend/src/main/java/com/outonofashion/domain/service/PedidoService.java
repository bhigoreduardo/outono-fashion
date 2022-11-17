package com.outonofashion.domain.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.pedido.PedidoNaoEncontradoException;
import com.outonofashion.domain.model.Cupom;
import com.outonofashion.domain.model.Pagamento;
import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CupomService cupomService;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public Pedido findById(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}

	@Transactional
	public Pedido save(Pedido pedido) {
		
		validarPedido(pedido);
		
		if (pedido.getDataPedido() == null) {
			
			//pedido = pedidoRepository.save(pedido);
			validarItens(pedido);
			//return pedido;
			
		}

		return pedidoRepository.save(pedido);

	}
	
	private void validarPedido(Pedido pedido) {
		
		if (pedido.getCupom() != null) {
			Long cupomId = pedido.getCupom().getId();
			Cupom cupom = cupomService.findById(cupomId);
			
			pedido.setCupom(cupom);
		}

		Long pagamentoId = pedido.getPagamento().getId();
		Pagamento pagamento = pagamentoService.findById(pagamentoId);

		Long usuarioId = pedido.getUsuario().getId();
		Usuario usuario = usuarioService.findById(usuarioId);
		
		pedido.setPagamento(pagamento);
		pedido.setUsuario(usuario);
	}

	private void validarItens(Pedido pedido) {
		pedido.setDataPedido(OffsetDateTime.now(ZoneOffset.UTC));
		
		pedido.getItensPedido().forEach(item -> {
			Produto produto = produtoService.findById(item.getProduto().getId());
			
			item.setProduto(produto);
			item.setPedido(pedido);
			itemPedidoService.save(item);
		});
		
	}
	
}
