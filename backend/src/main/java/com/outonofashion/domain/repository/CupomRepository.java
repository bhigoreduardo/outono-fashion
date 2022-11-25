package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
	
	public Optional<Cupom> findByNome(String nome);

}
