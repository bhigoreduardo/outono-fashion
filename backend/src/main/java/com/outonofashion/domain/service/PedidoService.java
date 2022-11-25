package com.outonofashion.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.PedidoNaoEncontradoException;
import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Cupom;
import com.outonofashion.domain.model.Estoque;
import com.outonofashion.domain.model.Pagamento;
import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tamanho;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.PedidoRepository;

@Service
public class PedidoService {

	//private final String CUPOM_INVALIDO = "Cupom %s é inválido.";

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired @Lazy
	private CupomService cupomService;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private TamanhoService tamanhoService;

	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private CorService corService;

	public Pedido findByCodigoPedido(String codigoPedido) {
		return pedidoRepository.findByCodigoPedido(codigoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}

	@Transactional
	public Pedido save(Pedido pedido) {

		validatePedido(pedido);
		validateItensPedido(pedido);
		
		pedido.calculateValorTotal();

		return pedidoRepository.save(pedido);
	}

	private void validatePedido(Pedido pedido) {
		/*
		if (pedido.getCupom() != null) {
			Cupom cupom = cupomService.findById(pedido.getCupom().getId());

			if (cupom.getAtivo() && cupom.getDataEncerramento().compareTo(OffsetDateTime.now()) > 0) {
				pedido.setCupom(cupom);
			} else {
				throw new NegocioException(String.format(CUPOM_INVALIDO, cupom.getNome()));
			}
		}
		*/
		
		if (pedido.getCupom() != null) {
			Cupom cupom = cupomService.findById(pedido.getCupom().getId());
			pedido.setCupom(cupom);
		}

		Pagamento pagamento = pagamentoService.findById(pedido.getPagamento().getId());
		Usuario usuario = usuarioService.findById(pedido.getUsuario().getId());

		

		pedido.setPagamento(pagamento);
		pedido.setUsuario(usuario);
	}

	private void validateItensPedido(Pedido pedido) {
		pedido.getItensPedido().forEach(item -> {
			Produto produto = produtoService.findById(item.getProduto().getId());
			Cor cor = corService.findById(item.getCor().getId());
			Tamanho tamanho = tamanhoService.findById(item.getTamanho().getId());
			
			Estoque estoque = estoqueService.findByProdutoAndCorAndTamanho(item.getProduto().getId(),
					item.getCor().getId(), item.getTamanho().getId());

			item.setProduto(produto);
			item.setCor(cor);
			item.setTamanho(tamanho);
			
			item.setPrecoUnitario(estoque.getPreco());
			item.setOferta(estoque.getOferta());
			
			item.setPedido(pedido);
		});
	}
	
	public Optional<Pedido> findByUsuarioAndCupom(Long usuarioId, Cupom cupom) {
		Usuario usuario = usuarioService.findById(usuarioId);
		
		return pedidoRepository.findByUsuarioAndCupom(usuario, cupom);
	}

}
