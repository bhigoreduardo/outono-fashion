package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Estoque;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tamanho;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	
	public Optional<Estoque> findByProdutoAndCorAndTamanho(Produto produto, Cor cor, Tamanho tamanho);

}
