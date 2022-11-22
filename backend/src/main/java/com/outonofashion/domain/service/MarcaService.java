package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.MarcaNaoEncontradaException;
import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.repository.MarcaRepository;

@Service
public class MarcaService {
	
	private final String MARCA_JA_CADASTRADO = "Marca descrição %s já foi cadastrada.";
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public List<Marca> findAll() {
		return marcaRepository.findAll();
	}
	
	public Marca findById(Long marcaId) {
		return marcaRepository.findById(marcaId)
				.orElseThrow(() -> new MarcaNaoEncontradaException(marcaId));
	}
	
	@Transactional
	public Marca save(Marca marca) {
		
		try {
			marca = marcaRepository.save(marca);
			marcaRepository.flush();
			return marca;
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(MARCA_JA_CADASTRADO, marca.getDescricao()));
		}
		
	}

}
