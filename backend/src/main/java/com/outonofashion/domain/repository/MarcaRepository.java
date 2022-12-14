package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>, MarcaRepositoryQueries {

	@Query(nativeQuery = true, value = "SELECT * FROM Marca m WHERE REPLACE(UNACCENT(LOWER(m.descricao)), '-', ' ') = REPLACE(UNACCENT(LOWER(:descricao)), '-', ' ')")
	public Optional<Marca> findByDescricao(String descricao);

}
