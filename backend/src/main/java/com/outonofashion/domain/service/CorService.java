package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.CorNaoEncontradaException;
import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.repository.CorRepository;

@Service
public class CorService {

	private final String COR_JA_CADASTRADO = "Cor descrição %s já foi cadastrada.";

	@Autowired
	private CorRepository corRepository;

	public List<Cor> findAll() {
		return corRepository.findAll();
	}

	public Cor findById(Long corId) {
		return corRepository.findById(corId)
				.orElseThrow(() -> new CorNaoEncontradaException(corId));
	}

	@Transactional
	public Cor save(Cor cor) {

		try {
			cor = corRepository.save(cor);
			corRepository.flush();
			return cor;
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(COR_JA_CADASTRADO, cor.getDescricao()));
		}

	}

}
