package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.pagamento.PagamentoJaCadastradoException;
import com.outonofashion.domain.exception.pagamento.PagamentoNaoEncontradoException;
import com.outonofashion.domain.model.Pagamento;
import com.outonofashion.domain.repository.PagamentoRepository;

@Service
public class PagamentoService {

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
			throw new PagamentoJaCadastradoException(pagamento.getDescricao());
		}
		
	}

}
