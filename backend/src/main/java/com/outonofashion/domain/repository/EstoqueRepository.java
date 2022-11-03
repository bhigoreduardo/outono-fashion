package com.outonofashion.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
