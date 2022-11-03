package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.outonofashion.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM Categoria c WHERE UNACCENT(LOWER(c.descricao)) = UNACCENT(LOWER(:descricao))")
	public Optional<Categoria> findByDescricao(String descricao);

}
