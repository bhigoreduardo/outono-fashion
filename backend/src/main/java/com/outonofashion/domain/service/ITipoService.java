package com.outonofashion.domain.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Tipo;

@Repository
public interface ITipoService {
	
	public List<Tipo> findByGeneroAndCategoria(String genero, String categoria);

}
