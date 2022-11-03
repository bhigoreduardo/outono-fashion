package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Cor;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM Cor c WHERE UNACCENT(LOWER(c.descricao)) = UNACCENT(LOWER(:descricao))")
	public Optional<Cor> findByDescricao(String descricao);

}
