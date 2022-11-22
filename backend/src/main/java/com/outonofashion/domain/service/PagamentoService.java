package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.exception.PagamentoNaoEncontradoException;
import com.outonofashion.domain.model.Pagamento;
import com.outonofashion.domain.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	private final String PAGAMENTO_JA_CADASTRADO = "Pagamento descrição %s já foi cadastrado.";

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public List<Pagamento> findAll() {
		return pagamentoRepository.findAll();
	}

	public Pagamento findById(Long pagamentoId) {
		return pagamentoRepository.findById(pagamentoId)
				.orElseThrow(() -> new PagamentoNaoEncontradoException(pagamentoId));
	}
	
	@Transactional
	public Pagamento save(Pagamento pagamento) {
		
		try {
			pagamento = pagamentoRepository.save(pagamento);
			pagamentoRepository.flush();
			
			return pagamento;		
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(PAGAMENTO_JA_CADASTRADO, pagamento.getDescricao()));
		}
		
	}
	
	@Transactional
	public void active(Long pagamentoId) {
		Pagamento pagamento = findById(pagamentoId);
		pagamento.active();
	}
	
	@Transactional
	public void inactive(Long pagamentoId) {
		Pagamento pagamento = findById(pagamentoId);
		pagamento.inactive();
	}

}
