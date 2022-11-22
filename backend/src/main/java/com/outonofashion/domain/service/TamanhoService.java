package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.exception.TamanhoNaoEncontradoException;
import com.outonofashion.domain.model.Tamanho;
import com.outonofashion.domain.repository.TamanhoRepository;

@Service
public class TamanhoService {

	private final String TAMANHO_JA_CADASTRADO = "Tamanho descrição %s já foi cadastrado.";

	@Autowired
	private TamanhoRepository tamanhoRepository;

	public List<Tamanho> findAll() {
		return tamanhoRepository.findAll();
	}

	public Tamanho findById(Long tamanhoId) {
		return tamanhoRepository.findById(tamanhoId)
				.orElseThrow(() -> new TamanhoNaoEncontradoException(tamanhoId));
	}

	@Transactional
	public Tamanho save(Tamanho tamanho) {

		try {
			tamanho = tamanhoRepository.save(tamanho);
			tamanhoRepository.flush();
			return tamanho;
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(TAMANHO_JA_CADASTRADO, tamanho.getDescricao()));
		}

	}

}
