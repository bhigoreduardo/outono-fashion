package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.CupomNaoEncontradoException;
import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.model.Cupom;
import com.outonofashion.domain.repository.CupomRepository;

@Service
public class CupomService {
	
	private final String CUPOM_INVALIDO = "Cupom %s é inválido.";
	
	private final String CUPOM_USADO = "Cupom %s já foi utilizado.";

	@Autowired
	private CupomRepository cupomRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	public Cupom findByNome(String cupomNome, Long usuarioId) {
		Cupom cupom = cupomRepository.findByNome(cupomNome)
				.orElseThrow(() -> new NegocioException(String.format(CUPOM_INVALIDO, cupomNome)));
		
		
		if (!cupom.getAtivo()) {
			throw new NegocioException(String.format(CUPOM_INVALIDO, cupomNome));
		}
		
		if (pedidoService.findByUsuarioAndCupom(usuarioId, cupom).isPresent()) {
			throw new NegocioException(String.format(CUPOM_USADO, cupomNome));
		}
		
		return cupom;
	}

	public List<Cupom> findAll() {
		return cupomRepository.findAll();
	}

	public Cupom findById(Long cupomId) {
		return cupomRepository.findById(cupomId)
				.orElseThrow(() -> new CupomNaoEncontradoException(cupomId));
	}

	@Transactional
	public Cupom save(Cupom cupom) {
		return cupom = cupomRepository.save(cupom);
	}

	@Transactional
	public void active(Long cupomId) {
		Cupom cupom = findById(cupomId);
		cupom.active();
	}

	@Transactional
	public void inactive(Long cupomId) {
		Cupom cupom = findById(cupomId);
		cupom.inactive();
	}
	
	@Transactional
	public void setTipoPorcentagem(Long cupomId) {
		Cupom cupom = findById(cupomId);
		cupom.setTipoPorcentagem();
	}
	
	@Transactional
	public void setTipoFixo(Long cupomId) {
		Cupom cupom = findById(cupomId);
		cupom.setTipoFixo();
	}

}
