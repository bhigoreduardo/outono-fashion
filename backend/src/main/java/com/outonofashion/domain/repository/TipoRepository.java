package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>, TipoRepositoryQueries {

	@Query(nativeQuery = true, value = "SELECT * FROM Tipo t WHERE UNACCENT(LOWER(t.descricao)) = UNACCENT(LOWER(:descricao))")
	public Optional<Tipo> findByDescricao(String descricao);
	
	/*
	@Query(nativeQuery = true, value = "SELECT t.descricao " +
									   "	FROM Produto p" +
									   "	INNER JOIN p.tipo t" +
									   "	INNER JOIN p.categoria c" +
									   "	INNER JOIN p.genero g" +
									   "	WHERE UNACCENT(LOWER(c.descricao)) = UNACCENT(LOWER(:categoria))" +
									   "		AND UNACCENT(LOWER(g.descricao)) = UNACCENT(LOWER(:genero))")
	public List<Tipo> findByDescricaoAndGenero(String descricao, String genero);
	*/

}
