package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.CategoriaNaoEncontradaException;
import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private final String CATEGORIA_JA_CADASTRADO = "Categoria descrição %s já foi cadastrada.";
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Long categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));
	}
	
	@Transactional
	public Categoria save(Categoria categoria) {
		
		try {
			categoria = categoriaRepository.save(categoria);
			categoriaRepository.flush();
			return categoria;
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(CATEGORIA_JA_CADASTRADO, categoria.getDescricao()));
		}
		
	}

}
