package com.outonofashion.domain.repository;

import java.util.List;

import com.outonofashion.domain.model.Marca;

public interface MarcaRepositoryQueries {

	List<Marca> findByGenero(String genero);
	
}
