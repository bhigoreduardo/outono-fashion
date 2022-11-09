package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM Produto p WHERE UNACCENT(LOWER(p.nome)) = UNACCENT(LOWER(REPLACE(:nome, '-', ' '))) AND id = :produtoId")
	public Optional<Produto> findByNomeAndId(String nome, Long produtoId);

	// CREATE EXTENSION unaccent;
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "SELECT * FROM Produto p WHERE LOWER(p.nome) = LOWER(REPLACE(:nome, '-', ' ')) AND id = :produtoId collate utf8_bin"
	 * ) public Produto findByNomeAndId(String nome, Long produtoId);
	 */

}
