package com.outonofashion.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.exception.TipoNaoEncontradoException;
import com.outonofashion.domain.model.Tipo;
import com.outonofashion.domain.repository.TipoRepository;

@Service
public class TipoService {

	private final String TIPO_JA_CADASTRADO = "Tipo descrição %s já foi cadastrado.";

	@Autowired
	private TipoRepository tipoRepository;

	public Tipo findById(Long tipoId) {
		return tipoRepository.findById(tipoId)
				.orElseThrow(() -> new TipoNaoEncontradoException(tipoId));
	}

	@Transactional
	public Tipo save(Tipo tipo) {

		try {
			tipo = tipoRepository.save(tipo);
			tipoRepository.flush();
			return tipo;
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(TIPO_JA_CADASTRADO, tipo.getDescricao()));
		}

	}

}
