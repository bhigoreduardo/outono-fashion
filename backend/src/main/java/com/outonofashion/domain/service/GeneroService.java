package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.genero.GeneroEmUsoException;
import com.outonofashion.domain.exception.genero.GeneroJaCadastroException;
import com.outonofashion.domain.exception.genero.GeneroNaoEncontradoException;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.repository.GeneroRepository;

@Service
public class GeneroService {
	
	private final String GENERO_EM_USO = "Gênero cód. %d está em uso.";
	
	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Genero> findAll() {
		return generoRepository.findAll();
	}
	
	public Genero findById(Long generoId) {
		return generoRepository.findById(generoId)
				.orElseThrow(() -> new GeneroNaoEncontradoException(generoId));
	}
	
	@Transactional
	public Genero save(Genero genero) {
		
		try {
			genero = generoRepository.save(genero);
			generoRepository.flush();
			return genero;
		} catch (DataIntegrityViolationException ex) {
			throw new GeneroJaCadastroException(genero.getDescricao());
		}
		
	}
	
	@Transactional
	public void deleteById(Long generoId) {
		
		try {
			generoRepository.deleteById(generoId);
			generoRepository.flush();
		} catch (EmptyResultDataAccessException ex) {
			throw new GeneroNaoEncontradoException(generoId);
		} catch (DataIntegrityViolationException ex) {
			throw new GeneroEmUsoException(String.format(GENERO_EM_USO, generoId));
		}
		
	}

}
