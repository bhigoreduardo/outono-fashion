package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Tamanho;

@Repository
public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM Tamanho t WHERE REPLACE(UNACCENT(LOWER(descricao)), '-', ' ') = REPLACE(UNACCENT(LOWER(:descricao)), '-', ' ')")
	public Optional<Tamanho> findByDescricao(String descricao);
	
}
