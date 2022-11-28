package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM Genero g WHERE REPLACE(UNACCENT(LOWER(descricao)), '-', ' ') = REPLACE(UNACCENT(LOWER(:descricao)), '-', ' ')")
	public Optional<Genero> findByDescricao(String descricao);

}
