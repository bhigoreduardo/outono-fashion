package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM Tipo t WHERE UNACCENT(LOWER(t.descricao)) = UNACCENT(LOWER(:descricao))")
	public Optional<Tipo> findByDescricao(String descricao);

}
