package com.outonofashion.domain.repository;

import java.util.List;

import com.outonofashion.domain.model.Tipo;

public interface TipoRepositoryQueries {
	
	public List<Tipo> findByGeneroAndCategoria(String genero, String categoria);

}
